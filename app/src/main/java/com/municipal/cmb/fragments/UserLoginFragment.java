package com.municipal.cmb.fragments;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.municipal.cmb.activity.LoginScreenActivity;
import com.municipal.cmb.activity.MemberScreenActivity;
import com.municipal.cmb.activity.R;
import com.municipal.cmb.activity.StaffScreenActivity;
import com.municipal.cmb.data.SQLiteHelper;
import com.municipal.cmb.helper.GlobalFunctions;

import java.util.regex.Pattern;

public class UserLoginFragment extends Fragment implements View.OnClickListener {
    // UI references.
    private EditText _txtUsernameOrMemberID;
    private EditText _txtPassword;
    private TextView _lnkNewMember;
    private Button _btnSignIn;
    private View _mProgressView;
    private View _viewLogin;
    private SQLiteHelper sqLiteHelper;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_login, container, false);
        initializeComponents(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((LoginScreenActivity) getActivity()).setActionBarTitle(R.string.login_fragment_title);
        initializeListeners();

        _txtPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_in:
                Toast.makeText(getActivity().getApplicationContext(), "Sign In", Toast.LENGTH_SHORT).show();
                getActivity().startActivity(new Intent(getActivity(), MemberScreenActivity.class)); //opening a activity from inside a fragment
                break;
            case R.id.lnk_new_member:
                Toast.makeText(getActivity().getApplicationContext(), "New Member", Toast.LENGTH_SHORT).show();
//                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.grow_from_middle_x_axis, R.anim.shrink_to_middle_x_axis).replace(android.R.id.content, new MemberRegistrationFragment()).commit();
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.grow_from_middle_x_axis, R.anim.shrink_to_middle_x_axis, R.anim.grow_from_middle_x_axis, R.anim.shrink_to_middle_x_axis)
                        .replace(android.R.id.content, new MemberRegistrationFragment())
                        .commit();

                break;
            default:
                break;
        }
    }

    private void initializeComponents(View view) {
        _txtUsernameOrMemberID = view.findViewById(R.id.txt_username_member_id_login);
        _txtPassword = view.findViewById(R.id.txt_password_login);
        _btnSignIn = view.findViewById(R.id.btn_sign_in);
        _mProgressView = view.findViewById(R.id.login_progress);
        _lnkNewMember = view.findViewById(R.id.lnk_new_member);
        _viewLogin = view.findViewById(R.id.view_user_login);
        sqLiteHelper = new SQLiteHelper(view.getContext(), "libraryDB", null, 1);


//        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/worksans-light.ttf");
//        _txtUsername.setTypeface(tf);
//        _txtPassword.setTypeface(globalFunctions.getCustomTypeFace("worksans-light"));
//        _lnkNewMember.setTypeface(globalFunctions.getCustomTypeFace("worksans-light"));
//        _btnSignIn.setTypeface(globalFunctions.getCustomTypeFace("worksans-light"));
    }

    private void initializeListeners() {
        _btnSignIn.setOnClickListener(this);
        _lnkNewMember.setOnClickListener(this);
    }


    private void showProgress(final boolean show) {
        // the progress spinner.
        // The ViewPropertyAnimator APIs are not available, so simply show
        // and hide the relevant UI components.
        _mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        _viewLogin.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        // Reset errors.
        _txtUsernameOrMemberID.setError(null);
        _txtPassword.setError(null);

        // Store values at the time of the login attempt.
        String user = _txtUsernameOrMemberID.getText().toString();
        String password = _txtPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            _txtPassword.setError(getString(R.string.error_invalid_password));
            focusView = _txtPassword;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(user)) {
            _txtUsernameOrMemberID.setError(getString(R.string.error_field_required));
            focusView = _txtUsernameOrMemberID;
            cancel = true;
        } else if (!isMemberIDValid(user)) {
            _txtUsernameOrMemberID.setError(getString(R.string.error_invalid_id));
            focusView = _txtUsernameOrMemberID;
            cancel = true;
        } else if (!doesMemberExist(user)) {
            _txtUsernameOrMemberID.setError(getString(R.string.error_member_not_found));
            focusView = _txtUsernameOrMemberID;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            //mAuthTask = new UserLoginTask(email, password);
            //mAuthTask.execute((Void) null);
        }
    }

    private boolean isMemberIDValid(String member_id) {
        return Pattern.matches("^[A-Z]{2}\\d{4}?$", member_id) || Pattern.matches("^[A-Za-z]+?$", member_id);
    }

    private boolean doesMemberExist(String member_id_or_username) {
        return sqLiteHelper.doesMemberExists(member_id_or_username);
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 6 && password.length() < 14;
    }


//    private void loginUser(String user, String password) {
//        String AdminEmail = "admin";
//        String AdminPass = "pass";
//        if (user.equals(AdminEmail) && password.equals(AdminPass)) {
//            Intent newIntent = new Intent(getActivity(), StaffScreenActivity.class);
//            startActivity(newIntent);
//            clearEditTextValues();
//        } else {
//            if (sqLiteHelper.checkEmail(Email)) {
//                if (sqLiteHelper.checkPassword(Email, Password)) {
//                    int UserId = sqLiteHelper.getUserId(Email);
//                    Intent newIntent = new Intent(LoginScreen.this, UserMenu.class);
//                    newIntent.putExtra("userId", UserId);
//                    startActivity(newIntent);
//                    clearEditTextValues();
//                    loginDialog.cancel();
//                } else {
//                    Toast.makeText(LoginScreen.this, "Your password is incorrect, please try again", Toast.LENGTH_SHORT).show();
//                    _txtPassword.requestFocus();
//                }
//            } else {
//                Toast.makeText(LoginScreen.this, "Your email does not exist", Toast.LENGTH_SHORT).show();
//                _txtUsernameOrMemberID.requestFocus();
//            }
//        }
//    }

    private void clearEditTextValues() {
        _txtUsernameOrMemberID.getText().clear();
        _txtPassword.getText().clear();
    }

}
