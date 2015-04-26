package chenyibin.yyc.utils;

import java.io.IOException;
import java.io.OutputStream;

public class NullOutputStream extends OutputStream
{
	static NullOutputStream nuller;
	
	public synchronized static NullOutputStream getStream()
	{
		if (nuller == null) {
			nuller = new NullOutputStream();
		}
		return nuller;
	}
	
	@Override
	public void write(int b) throws IOException {
		// Do absolutely nothing
	}
}
