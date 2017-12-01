package com.cony.security.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangk-p on 2017/8/30.
 */
public class DataCache<K, V> {

    private Map<K, V> map = new ConcurrentHashMap<K, V>();
    private DelayQueue<DelayedItem<K>> queue = new DelayQueue<DelayedItem<K>>();
    private long liveTime;

    public DataCache(long liveTime){
        this.liveTime = liveTime;
        Thread t = new Thread(){
            @Override
            public void run(){
                dameonCheckOverdueKey();
            }
        };
        t.setDaemon(true);
        t.start();
    }

    public void put(K k,V v){
        V v2 = map.put(k, v);
        DelayedItem<K> tmpItem = new DelayedItem<K>(k, liveTime);
        if (v2 != null) {
            queue.remove(tmpItem);
        }
        queue.put(tmpItem);
    }

    public V get(K k){
        V v = map.get(k);
        if(v != null) {
            put(k,v);
        }
        return v;
    }

    public void dameonCheckOverdueKey(){
        while (true) {
            DelayedItem<K> delayedItem = queue.poll();
            if (delayedItem != null) {
                map.remove(delayedItem.getT());
                System.out.println(System.nanoTime()+" remove "+delayedItem.getT() +" from cache");
            }
            try {
                Thread.sleep(300);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

}

class DelayedItem<T> implements Delayed {

    private T t;
    private long liveTime ;
    private long removeTime;

    public DelayedItem(T t,long liveTime){
        this.setT(t);
        this.liveTime = TimeUnit.NANOSECONDS.convert(liveTime,TimeUnit.SECONDS);
        this.removeTime = this.liveTime + System.nanoTime();
    }

    @Override
    public int compareTo(Delayed o) {
        if (o == null) return 1;
        if (o == this) return  0;
        if (o instanceof DelayedItem){
            DelayedItem<T> tmpDelayedItem = (DelayedItem<T>)o;
            if (liveTime > tmpDelayedItem.liveTime ) {
                return 1;
            }else if (liveTime == tmpDelayedItem.liveTime) {
                return 0;
            }else {
                return -1;
            }
        }
        long diff = getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
        return diff > 0 ? 1:diff == 0? 0:-1;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(removeTime - System.nanoTime(), unit);
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
    @Override
    public int hashCode(){
        return t.hashCode();
    }

    @Override
    public boolean equals(Object object){
        if (object instanceof DelayedItem) {
            return object.hashCode() == hashCode() ?true:false;
        }
        return false;
    }

}