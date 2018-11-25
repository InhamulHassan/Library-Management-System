package com.municipal.cmb.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.municipal.cmb.activity.LoginScreenActivity;
import com.municipal.cmb.activity.MemberScreenActivity;
import com.municipal.cmb.activity.R;
import com.municipal.cmb.activity.StaffScreenActivity;

import de.hdodenhof.circleimageview.CircleImageView;


public class MemberRegistrationFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    private CircleImageView _roundeUserAvatar;
    private EditText _txtUsername;
    private EditText _txtMemberID;
    private EditText _txtPassword;
    private EditText _txtConfirmPassword;
    private AppCompatButton _btnSignUp;
    private TextView _lnkUserLogin;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MemberRegistrationFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MemberRegistrationFragment newInstance(String param1, String param2) {
        MemberRegistrationFragment fragment = new MemberRegistrationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((LoginScreenActivity) getActivity()).setActionBarTitle(R.string.registration_fragment_title);
        View view = inflater.inflate(R.layout.fragment_member_registration, container, false);
        initializeComponents(view);
        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void initializeComponents(View view) {
        _txtMemberID = view.findViewById(R.id.txt_member_id_register);
        _txtUsername = view.findViewById(R.id.txt_username_register);
        _txtPassword = view.findViewById(R.id.txt_password_login);
        _txtConfirmPassword = view.findViewById(R.id.txt_password_confirm_register);
        _btnSignUp = view.findViewById(R.id.btn_sign_up);
        _roundeUserAvatar = view.findViewById(R.id.img_user_avatar);
        _lnkUserLogin = view.findViewById(R.id.lnk_user_login);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initializeListeners();
        super.onViewCreated(view, savedInstanceState);
    }

    private void initializeListeners() {
        _btnSignUp.setOnClickListener(this);
        _lnkUserLogin.setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_up:
                Toast.makeText(getActivity().getApplicationContext(), "Sign Up", Toast.LENGTH_SHORT).show();
                getActivity().startActivity(new Intent(getActivity(), StaffScreenActivity.class)); //opening a activity from inside a fragment
                break;
            case R.id.lnk_user_login:
                Toast.makeText(getActivity().getApplicationContext(), "Have Account", Toast.LENGTH_SHORT).show();
//                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.grow_from_middle_x_axis, R.anim.shrink_to_middle_x_axis).replace(android.R.id.content, new UserLoginFragment()).commit();
                getActivity().
                        getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.grow_from_middle_x_axis, R.anim.shrink_to_middle_x_axis, R.anim.grow_from_middle_x_axis, R.anim.shrink_to_middle_x_axis)
                        .replace(android.R.id.content, new UserLoginFragment())
                        .commit();
                break;
            default:
                break;
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
