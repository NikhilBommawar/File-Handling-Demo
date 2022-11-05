package com.example;

import java.io.ByteArrayOutputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;


public class ImageUtil {

	//compression of file by using Deflater
	//we get a compressed byte array of an image
	public static byte[] compressImage(byte[] data) {
		Deflater deflater =new Deflater();
		deflater.setLevel(Deflater.BEST_COMPRESSION);
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream out=new ByteArrayOutputStream(data.length);
		byte[] tmp=new byte[4*1024];
		while(!deflater.finished()) {
			int size=deflater.deflate(tmp);
			out.write(tmp,0,size);
		}
		return out.toByteArray();
	}
	
	
	//decompression
	
	//data in the byte array and decompress it to the array of original size
	public static byte[] decompression(byte[] data) throws DataFormatException {
		Inflater inflater=new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream out=new ByteArrayOutputStream(data.length);
		byte[] tmp=new byte[4*1024];
		while(!inflater.finished()) {
			int size=inflater.inflate(tmp);
			out.write(tmp,0,size);
		}
		return out.toByteArray();
	}
	
	
}

