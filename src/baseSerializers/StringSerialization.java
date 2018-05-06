package baseSerializers;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.util.HashSet;

import serializer.ValueSerializer;

public class StringSerialization implements ValueSerializer{

	@Override
	public void objectToBuffer(Object anOutputBuffer, Object anObject, HashSet<Object> visitedObjects)
			throws NotSerializableException {
		if(anOutputBuffer instanceof ByteBuffer) {
			String str = (String) anObject;
			((ByteBuffer) anOutputBuffer).putInt(str.length());
			((ByteBuffer) anOutputBuffer).put(str.getBytes());
		}
	}

	@Override
	public Object objectFromBuffer(Object anInputBuffer, Class aClass, HashSet<Object> retrievedObjects)
			throws StreamCorruptedException, NotSerializableException {
		if(anInputBuffer instanceof ByteBuffer) {
			
			byte[] strArr = new byte[((ByteBuffer) anInputBuffer).getInt()];
			((ByteBuffer) anInputBuffer).get(strArr);
			return new String(strArr);
		}
		
		return null;
	}
	

}
