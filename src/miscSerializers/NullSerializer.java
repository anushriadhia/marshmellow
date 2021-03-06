package miscSerializers;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.util.HashSet;

import serializer.ValueSerializer;
import util.annotations.Tags;
import static util.annotations.Comp533Tags.NULL_SERIALIZER;


@Tags({NULL_SERIALIZER})
public class NullSerializer implements ValueSerializer{


	@Override
	public void objectToBuffer(Object anOutputBuffer, Object anObject, HashSet<Object> visitedObjects)
			throws NotSerializableException {
		//doNothing?
	}

	@Override
	public Object objectFromBuffer(Object anInputBuffer, Class aClass, HashSet<Object> retrievedObjects)
			throws StreamCorruptedException, NotSerializableException {
		return null;
	}
	

}
