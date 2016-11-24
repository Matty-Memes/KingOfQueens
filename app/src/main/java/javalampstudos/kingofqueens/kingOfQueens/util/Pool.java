package javalampstudos.kingofqueens.kingOfQueens.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 24/11/2016.
 */

public class Pool<T> {
    public interface ObjectFactory<T> {
        public T createObject();

    }

    private final ObjectFactory<T> mFactory;

    private final List<T> mPool;
    private final int mMaxPoolSize;

    public Pool(ObjectFactory<T> factory, int maxPoolSize) {
        mFactory = factory;
        mMaxPoolSize = maxPoolSize;
        mPool = new ArrayList<T>(mMaxPoolSize);
    }

    public T get() {
        T object = null;

        if (mPool.isEmpty())
            object = mFactory.createObject();
        else
            object = mPool.remove(mPool.size() - 1);

        return object;
    }
    public void add(T object) {
        if (mPool.size() < mMaxPoolSize)
            mPool.add(object);
    }


}
