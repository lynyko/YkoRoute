package com.yko.route;

import java.util.Map;

public interface Controller {
    void start(Object from, Map<String, Object> params, ResultCallback callback);

    public interface ResultCallback{
        void callback(Object result);
    }
}
