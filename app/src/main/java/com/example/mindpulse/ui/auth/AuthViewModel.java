package com.example.mindpulse.ui.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mindpulse.data.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class AuthViewModel extends ViewModel {

    private final FirebaseAuth auth = FirebaseAuth.getInstance();
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private final MutableLiveData<FirebaseUser> userLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);

    public AuthViewModel() {
        userLiveData.setValue(auth.getCurrentUser());
    }

    public LiveData<FirebaseUser> getUserLiveData() {
        return userLiveData;
    }

    public LiveData<String> getErrorLiveData() {
        return errorLiveData;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void login(String email, String password) {
        isLoading.setValue(true);
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    isLoading.setValue(false);
                    if (task.isSuccessful()) {
                        userLiveData.setValue(auth.getCurrentUser());
                    } else {
                        errorLiveData.setValue(task.getException() != null ? task.getException().getMessage() : "Login failed");
                    }
                });
    }

    public void signUp(String fullName, String email, String password) {
        isLoading.setValue(true);
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = auth.getCurrentUser();
                        if (firebaseUser != null) {
                            User user = new User(firebaseUser.getUid(), fullName, email);
                            saveUserToFirestore(user);
                        }
                    } else {
                        isLoading.setValue(false);
                        errorLiveData.setValue(task.getException() != null ? task.getException().getMessage() : "Registration failed");
                    }
                });
    }

    private void saveUserToFirestore(User user) {
        db.collection("users").document(user.getId())
                .set(user)
                .addOnCompleteListener(task -> {
                    isLoading.setValue(false);
                    if (task.isSuccessful()) {
                        userLiveData.setValue(auth.getCurrentUser());
                    } else {
                        errorLiveData.setValue("Failed to save user profile");
                    }
                });
    }

    public void logout() {
        auth.signOut();
        userLiveData.setValue(null);
    }
}