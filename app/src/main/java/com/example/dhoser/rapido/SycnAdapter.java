package com.example.dhoser.rapido;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.content.SyncResult;
import android.os.Bundle;
import android.widget.Toast;

import com.example.dhoser.rapido.modelo.para_subidatos;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.DataAsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public    class SycnAdapter   extends AbstractThreadedSyncAdapter {

public static Context ctx;


    public SycnAdapter(Context context, boolean autoInitialize) {

        super(context, autoInitialize);
        ctx=context;
    }

    public SycnAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
    }

    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {

ja();

}
public void ja(){
        para_subidatos h=new para_subidatos(ctx);
        h.da();
}

}
