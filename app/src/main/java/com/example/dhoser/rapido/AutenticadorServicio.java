package com.example.dhoser.rapido;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.net.Authenticator;

public    class AutenticadorServicio  extends Service {
    // Instance field that stores the authenticator object
    private AutenticadorChalla mAuthenticator;
    @Override
    public void onCreate() {
        // Create a new authenticator object
        mAuthenticator = new AutenticadorChalla(this);
    }
    /*
     * When the system binds to this Service to make the RPC call
     * return the authenticator's IBinder.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }
}
