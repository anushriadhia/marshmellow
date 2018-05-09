package baseSerializers;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.util.HashSet;

import serializer.DispatchingSerializer;
import serializer.SerializerRegistry;
import serializer.TextBuffer;
import serializer.ValueSerializer;

public class HashSetSerializer implements ValueSerializer {

	@SuppressWarnings("unchecked")
	@Override
	public void objectToBuffer(Object anOutputBuffer, Object anObject, HashSet<Object> visitedObjects)
			throws NotSerializableException {
		
		int size = ((HashSet) anObject).size();
		
		if(anOutputBuffer instanceof ByteBuffer) {
			((ByteBuffer) anOutputBuffer).putInt(size);
		} else if (anOutputBuffer instanceof TextBuffer) {
			((TextBuffer) anOutputBuffer).put(size);
		}
		
		DispatchingSerializer dispatcher = SerializerRegistry.getDispatchingSerializer();
		
		((HashSet) anObject).forEach((node)-> {
			try {
				dispatcher.ObjectToBuffer(anOutputBuffer, node, visitedObjects);
			} catch (NotSerializableException e) {
				e.printStackTrace();
			}
		});
	}

	@Override
	public Object objectFromBuffer(Object anInputBuffer, Class aClass, HashSet<Object> retrievedObjects)
			throws StreamCorruptedException, NotSerializableException {
		
		
		//not the way to do this, fix this shit
		DispatchingSerializer dispatcher = SerializerRegistry.getDispatchingSerializer();
	
		HashSet<Object> newSet = new HashSet<Object>();
		while(anInputBuffer != null) {
			newSet.add(dispatcher.objectFromBuffer(anInputBuffer, retrievedObjects));
		}
		
		return newSet;
	}

}
