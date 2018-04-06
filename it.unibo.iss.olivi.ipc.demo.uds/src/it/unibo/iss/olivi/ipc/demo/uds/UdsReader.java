package it.unibo.iss.olivi.ipc.demo.uds;

import java.io.File;
import java.io.IOException;

import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.charset.Charset;

import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;

import jnr.enxio.channels.NativeSelectorProvider;
import jnr.unixsocket.UnixServerSocketChannel;
import jnr.unixsocket.UnixSocketAddress;
import jnr.unixsocket.UnixSocketChannel;

public class UdsReader {

	// The file system name of the Unix Domain server socket used to accept connection requests
	private static final String UDS_PATH = "." + File.separator + ".uds.sock";
	
	
	// This main contains a single thread server based on a selector and asynchronous channels (backed by Unix Domain Sockets)
	public static void main(String[] args) {
		UnixSocketAddress 		path 			= createUnixSocketAddress(UDS_PATH);
		UnixServerSocketChannel serverChannel 	= null; 
		Selector 				channelSelector = null;
		try {
			serverChannel = UnixServerSocketChannel.open();
			serverChannel.configureBlocking(false);
			try {
				serverChannel.socket().bind(path);
			} catch (IOException e2) {
				System.out.println(e2.getMessage());
				e2.printStackTrace();
			}
			
			channelSelector = NativeSelectorProvider.getInstance().openSelector();
			serverChannel.register(channelSelector, SelectionKey.OP_ACCEPT, new Consumer<SelectionKey>() {
				final class ClientReadyReadHandler implements Consumer<SelectionKey>{
					@Override
					public void accept(SelectionKey key) {
						UnixSocketChannel 	clientChannel = (UnixSocketChannel) key.channel();
						ByteBuffer 			buf 		  = ByteBuffer.allocate(1024);
						try {
							int readBytes = clientChannel.read(buf);
							if (readBytes > 0) {
								buf.flip();
								System.out.println("Received message: " + Charset.forName("ISO-8859-1").decode(buf).toString());
							} else if (readBytes == -1) {
								System.out.println("A client closed its connection");
								clientChannel.close();
							}
						} catch (IOException e) {
							e.printStackTrace();
							try {
								clientChannel.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							} 
						}
					}
				}
				
				private final ClientReadyReadHandler clientReadyReadHandler = new ClientReadyReadHandler();
				
				@Override
				public void accept(SelectionKey key) {
					UnixSocketChannel clientChannel = null;
					try {
						clientChannel = ((UnixServerSocketChannel) key.channel()).accept();
						clientChannel.configureBlocking(false);
						clientChannel.register(key.selector(), SelectionKey.OP_READ, clientReadyReadHandler);
					} catch (ClosedChannelException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			
			System.out.println("Server is now accepting connections");
			while (channelSelector.select() > 0) {
			
				Set<SelectionKey> 		selectedKeys 		 = channelSelector.selectedKeys();
				Iterator<SelectionKey> 	selectedKeysIterator = selectedKeys.iterator();
				
				while (selectedKeysIterator.hasNext()) {
					SelectionKey currentKey = selectedKeysIterator.next();
					if (currentKey.isValid()) {
						// This warning can be safely ignored, as all the objects attached to the selection keys implement Consumer<SelectionKey>
						@SuppressWarnings("unchecked")
						Consumer<SelectionKey> readyChannelHandler = (Consumer<SelectionKey>) currentKey.attachment();
						readyChannelHandler.accept(currentKey);
					}
					selectedKeysIterator.remove();
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	private static UnixSocketAddress createUnixSocketAddress(final String path) {
		File lPath = new File(path);
		lPath.deleteOnExit();
	    return new UnixSocketAddress(lPath);		
	}
}
