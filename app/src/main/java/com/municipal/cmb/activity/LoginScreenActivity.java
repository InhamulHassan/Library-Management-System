package com.municipal.cmb.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.municipal.cmb.fragments.MemberRegistrationFragment;
import com.municipal.cmb.fragments.UserLoginFragment;


public class LoginScreenActivity extends AppCompatActivity implements MemberRegistrationFragment.OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, new UserLoginFragment()).commit();
        }
    }

    @Override
    public void onBackPressed() {
//        Log.d("BACKSTACKSUPP", String.valueOf(getSupportFragmentManager().getBackStackEntryCount()));
//        Log.d("BACKSTACK", String.valueOf(getFragmentManager().getBackStackEntryCount()));
//        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
//            getSupportFragmentManager().popBackStack();
//        } else {
//            super.onBackPressed();
//        }
        super.onBackPressed();
    }
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.lnk_new_member:
//                //lnkNewMember.setSelected(true);
//                //lnkNewMember.setTextColor(getResources().getColor(R.color.black));
//                Toast.makeText(this, "JJDJJDJDJDJDJ", Toast.LENGTH_SHORT).show();
//                getSupportFragmentManager().beginTransaction().add(R.id.login_frame, new MemberRegistrationFragment()).commit();
//                break;
//            case R.id.btn_sign_in:
//                //attemptLogin();
//                Intent intent = new Intent(LoginScreenActivity.this, MemberScreenActivity.class);
//                startActivity(intent);
//                break;
//        }
//    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void setActionBarTitle(int title) {
        getSupportActionBar().setTitle(title);
    }


    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
//    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
//
//        private final String mEmail;
//        private final String mPassword;
//
//        UserLoginTask(String email, String password) {
//            mEmail = email;
//            mPassword = password;
//        }
//
//        @Override
//        protected Boolean doInBackground(Void... params) {
//            // TODO: attempt authentication against a network service.
//
//            try {
//                // Simulate network access.
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                return false;
//            }
//
//            for (String credential : DUMMY_CREDENTIALS) {
//                String[] pieces = credential.split(":");
//                if (pieces[0].equals(mEmail)) {
//                    // Account exists, return true if the password matches.
//                    return pieces[1].equals(mPassword);
//                }
//            }
//
//            // TODO: register the new account here.
//            return true;
//        }
//
//        @Override
//        protected void onPostExecute(final Boolean success) {
//            mAuthTask = null;
//            showProgress(false);
//
//            if (success) {
//                finish();
//            } else {
//                mPasswordView.setError(getString(R.string.error_incorrect_password));
//                mPasswordView.requestFocus();
//            }
//        }
//
//        @Override
//        protected void onCancelled() {
//            mAuthTask = null;
//            showProgress(false);
//        }
//    }
}

