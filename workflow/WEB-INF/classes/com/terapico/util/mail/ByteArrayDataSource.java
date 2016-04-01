package com.terapico.util.mail;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataSource;

public class ByteArrayDataSource implements DataSource {
	private static final String OCTET_STREAM = "application/octet-stream";

	private byte[] data;

	private String contentType;

	public ByteArrayDataSource(byte[] data) {
		this(data, OCTET_STREAM);
	}
	

	public ByteArrayDataSource(byte[] data, String contentType) {
		this.data = data;
		this.contentType = contentType;
	}

	public String getContentType() {
		return contentType;
	}

	public InputStream getInputStream() {
		return new ByteArrayInputStream(data);
	}

	public String getName() {
		return "ByteArrayDataSource";
	}

	public OutputStream getOutputStream() throws IOException {
		throw new FileNotFoundException();
	}
}