package cn.eric.basicore.app;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.v4.util.SparseArrayCompat;

import com.bumptech.glide.request.RequestOptions;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by eric on 2018/6/9
 */
public final class Configurator {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({BASE_URL, APP_CONTEXT, IMG_REQUEST_OPTIONS})
    @interface ConfigKey {
    }

    private static final int BASE_URL = 0;
    private static final int APP_CONTEXT = 1;
    private static final int IMG_REQUEST_OPTIONS = 2;
    private boolean isConfig = false;

    private static final SparseArrayCompat<Object> sConfigs = new SparseArrayCompat<>();

    public static Configurator get() {
        return ConfiguratorHolder.sInstance;
    }

    private Configurator() {
    }

    public Configurator baseUrl(String baseUrl) {
        setConfig(BASE_URL, baseUrl);
        return this;
    }

    public Configurator appContext(Context appContext) {
        setConfig(APP_CONTEXT, appContext);
        return this;
    }

    public Configurator imagePlaceHolder(@DrawableRes int loadingRes, @DrawableRes int errorRes) {
        RequestOptions requestOptions = getRequestOptions().placeholder(loadingRes).error(errorRes);
        setConfig(IMG_REQUEST_OPTIONS, requestOptions);
        return this;
    }

    public RequestOptions getRequestOptions() {
        RequestOptions requestOptions = (RequestOptions) sConfigs.get(IMG_REQUEST_OPTIONS);
        if (requestOptions == null) {
            requestOptions = new RequestOptions();
        }

        return requestOptions;
    }

    public String getBaseUrl() {
        return (String) getConfig(BASE_URL);
    }

    public Context getAppContext() {
        return (Context) getConfig(APP_CONTEXT);
    }

    public void configure() {
        isConfig = true;
    }


    private void setConfig(@ConfigKey int key, Object value) {
        sConfigs.put(key, value);
    }

    private void checkConfig() {
        if (!isConfig) {
            throw new RuntimeException("Configuration is not ready, please call configure().");
        }
    }

    private Object getConfig(@ConfigKey int key) {
        checkConfig();
        final Object value = sConfigs.get(key);
        if (value == null) {
            throw new NullPointerException(key + " IS NULL");
        }
        return sConfigs.get(key);
    }

    private static class ConfiguratorHolder {
        private static final Configurator sInstance = new Configurator();
    }
}
