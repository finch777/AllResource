package com.levi.algorithm_practice.testdisruptor.perf;

public class PoolObjectBase implements java.io.Closeable{
    /**

     */
    protected IObjectPool Pool;
    public final IObjectPool getPool() {
        return Pool;
    }
    public final void setPool(IObjectPool value) {
        Pool = value;
    }

    /**

     */
    protected boolean InPool;
    public final boolean getInPool() {
        return InPool;
    }
    public final void setInPool(boolean value) {
        InPool = value;
    }

    /**

     */
    public void close() {
        if (this.Pool != null)
            this.Pool.PutOneObject(this);
    }
}
