/*
 * Created on 2004-9-26
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.terapico.util;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.nio.LongBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class TeraPicoSerialNumberGenerator {

	private static long unqid = 0xAAAAAA;
	private static LongBuffer ibuffer;
	private static int first = 0;
	static {
		try {
			RandomAccessFile file =
				new RandomAccessFile("./conf/localstore/serialnumber.dat", "rw");

			//file.setLength (4*4096);

			FileChannel channel = file.getChannel();

			MappedByteBuffer buffer =
				channel.map(FileChannel.MapMode.READ_WRITE, 0, channel.size());

			buffer.order(ByteOrder.nativeOrder());
			ibuffer = buffer.asLongBuffer();
			channel.close();
			file.close();
		} catch (IOException e) {

		}
	}


	public synchronized static long getNext(int index) {

		unqid = ibuffer.get(index);
		unqid++;
		ibuffer.put(index, unqid);
		return unqid;

		//return 0;
	}
	public synchronized static long next(int index) throws IOException {
		return getNext(index);
	}

}
