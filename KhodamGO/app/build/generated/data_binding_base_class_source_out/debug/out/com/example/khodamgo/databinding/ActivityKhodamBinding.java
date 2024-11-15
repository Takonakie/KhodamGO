// Generated by view binder compiler. Do not edit!
package com.example.khodamgo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.khodamgo.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import io.github.sceneview.ar.ArSceneView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityKhodamBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final ExtendedFloatingActionButton place;

  @NonNull
  public final ArSceneView sceneView;

  private ActivityKhodamBinding(@NonNull ConstraintLayout rootView, @NonNull ConstraintLayout main,
      @NonNull ExtendedFloatingActionButton place, @NonNull ArSceneView sceneView) {
    this.rootView = rootView;
    this.main = main;
    this.place = place;
    this.sceneView = sceneView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityKhodamBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityKhodamBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_khodam, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityKhodamBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.place;
      ExtendedFloatingActionButton place = ViewBindings.findChildViewById(rootView, id);
      if (place == null) {
        break missingId;
      }

      id = R.id.sceneView;
      ArSceneView sceneView = ViewBindings.findChildViewById(rootView, id);
      if (sceneView == null) {
        break missingId;
      }

      return new ActivityKhodamBinding((ConstraintLayout) rootView, main, place, sceneView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
