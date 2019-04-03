package com.example.nguye.capston1_dtu.Controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguye.capston1_dtu.R;
import com.example.nguye.capston1_dtu.common.Validate;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textViewDK,btnForgot,btnDangKy;
    private Button btnDangNhapbutton,btnFB;
    private LoginButton loginButton;
    private SignInButton btnGoogleButton;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private ProgressDialog progressDialog;
    private DatabaseReference mDatabase;
    private FirebaseDatabase mFirebase;
    private TextInputLayout textInputEmail,textInputPassword;
    private GoogleSignInClient mGoogle;
    private static MainActivity mainActivity;
    private static final String TAG = "FacebookLogin";
    private CallbackManager callbackManager;
    private FacebookCallback<LoginResult> loginResult;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialog = new ProgressDialog(this);
        mDatabase = FirebaseDatabase.getInstance().getReference("Users");
        Anhxa();
        ConfigureGGsignin();
        btnGoogleButton.setOnClickListener(this);
        btnDangNhapbutton.setOnClickListener(this);
        btnForgot.setOnClickListener(this);
        btnDangKy.setOnClickListener(this);
        firebaseUser = mAuth.getCurrentUser();
         checkEmail();
        initFaceBook();
        printKeyHash(this);
    }
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
    public void checkEmail(){
        if(firebaseUser==null){
            FacebookSdk.sdkInitialize(getApplicationContext());
            textInputEmail.getEditText().setText(null);
            callbackManager = CallbackManager.Factory.create();
            loginButton.setReadPermissions(Arrays.asList("email"));
        }
        else{

                textInputEmail.getEditText().setText(firebaseUser.getEmail()+"");
                startActivity(new Intent(LoginActivity.this, MainActivity_Chinh.class));


        }
    }

    public void ConfigureGGsignin(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogle = GoogleSignIn.getClient(this, gso);
        mAuth = FirebaseAuth.getInstance();
    }
    public void buttonClickFB(View v){
        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = findViewById(R.id.btnLoginFb);
        loginButton.setReadPermissions("email", "public_profile");
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookToken(loginResult.getAccessToken());
            }
            @Override
            public void onCancel() {
            }
            @Override
            public void onError(FacebookException error) {
            }
        });
    }
    private void handleFacebookToken(AccessToken accessToken){
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(LoginActivity.this, MainActivity_Chinh.class));
                    Toast.makeText(LoginActivity.this, "ĐĂNG NHẬP VỚI TÀI KHOẢN FB THÀNH CÔNG", Toast.LENGTH_SHORT).show();
                    //firebaseUser = mAuth.getCurrentUser();
//                    updateUI(firebaseUser);
                }
                else{
                    Toast.makeText(LoginActivity.this, "COULD NOT register to FIRE", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
//
//    private void updateUI(FirebaseUser firebaseUser) {
//
//    }
    public static void shareLinkFB(String title, String linkShare, String imgThumnal) {
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentTitle(title)
                .setImageUrl(Uri.parse(imgThumnal))
                .setContentUrl(Uri.parse(linkShare))
                .setShareHashtag(new ShareHashtag.Builder()
                        .setHashtag("#AndroidCoBan.Com")
                        .build())
                .build();
        ShareDialog.show(mainActivity, content);
    }
    public static void sharePhoto(Bitmap b, String caption) {
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(b)
                .setCaption(caption)
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo).build();
        ShareDialog.show(mainActivity, content);
    }
    public URL extractFacebookIcon(String id) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            URL imageURL = new URL("http://graph.facebook.com/" + id
                    + "/picture?type=large");
            return imageURL;
        } catch (Throwable e) {
            return null;
        }
    }
    public void initFaceBook () {
        loginResult = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //Login thành công xử lý tại đây
                GraphRequest request = GraphRequest.newMeRequest(
                        AccessToken.getCurrentAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object,
                                                    GraphResponse response) {
                                // Application code
                                String name = object.optString(getString(R.string.name));
                                String id = object.optString(getString(R.string.id));
                                String email = object.optString(getString(R.string.email));
                                String link = object.optString(getString(R.string.link));
                                URL imageURL = extractFacebookIcon(id);
                                Log.d("name: ",name);
                                Log.d("id: ",id);
                                Log.d("email: ",email);
                                Log.d("link: ",link);
                                Log.d("imageURL: ",imageURL.toString());

                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString(getString(R.string.fields), getString(R.string.fields_name));
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        };
    }
    public boolean isLoggedInFaceBook() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 1) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {

            }
        }
        else{
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }

    }
    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (android.content.pm.Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(LoginActivity.this, MainActivity_Chinh.class));
                            Toast.makeText(getApplicationContext(), "USER LOGGED IN SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "COULD NOT LOG IN USER", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }

    public void Anhxa(){
        btnDangKy=findViewById(R.id.btnDangKilogin);
        btnDangNhapbutton = findViewById(R.id.btnDangnhap);
        btnForgot=findViewById(R.id.btnforgot);
        btnGoogleButton= findViewById(R.id.btnGoogle);
        textInputEmail = findViewById(R.id.textInput_Email);
        textInputPassword=findViewById(R.id.textInput_Password);
        loginButton=findViewById(R.id.btnLoginFb);
    }
    private void DangNhapLogin(){
        String email = textInputEmail.getEditText().getText().toString().trim();
        String password = textInputPassword.getEditText().getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Enter Pass", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("LOGIN");
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        if(mAuth.getCurrentUser().isEmailVerified()){
                            startActivity(new Intent(LoginActivity.this, MainActivity_Chinh.class));
                        }else{
                            Toast.makeText(LoginActivity.this, "BẠN CHƯA XÁC THỰC EMAIL, VÀO TÀI KHOẢN EMAIL ĐỂ XÁC THỰC LẠI"
                                    , Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    }else{
                        Toast.makeText(LoginActivity.this, "Email hoặc mật khẩu bạn nhập không đúng, nhập lại"
                                , Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                }
            });
    }

    private void signInGooGle(){
        Intent intent = mGoogle.getSignInIntent();
        startActivityForResult(intent, 1);
    }
    @Override
    public void onClick(View v) {
        //int i = v.getId();
        Validate validate = new Validate();
        String email = textInputEmail.getEditText().getText().toString().trim();
        String password = textInputPassword.getEditText().getText().toString().trim();
        if(v==btnDangNhapbutton){
            if(validate.validateEmail(email,textInputEmail) && validate.validatePassword(password, textInputPassword)
                    ){
                DangNhapLogin();
                return;
            }
//            String input = "FullName: " + textInputNAME.getEditText().getText().toString();
//            input += "\n";
//            input += "Email " + textInputEmail.getEditText().getText().toString();
//            input += "\n";
//            input += "Password: " + textInputPassword.getEditText().getText().toString();
//            input += "\n";
//            input +="Confirm Password: " + textInputConfirmPassword.getEditText().getText().toString();
//            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
        }
        else if(v==btnGoogleButton){
            signInGooGle();

        }
        else if(v==btnDangKy){
             startActivity(new Intent(LoginActivity.this,MainActivity.class));

        }
        else if(v==btnForgot){
            startActivity(new Intent(LoginActivity.this,forgot_password.class));
        }


    }
}
