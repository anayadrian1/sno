package edu.cnm.deepdive.sno.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.AppBarConfiguration.Builder;
import androidx.navigation.ui.NavigationUI;
import edu.cnm.deepdive.sno.R;
import edu.cnm.deepdive.sno.databinding.ActivityNavigationBinding;
import edu.cnm.deepdive.sno.service.GoogleSignInService;
import org.jetbrains.annotations.NotNull;

/**
 * Main activity to handle navigation between fragments in the application via bottom button
 * navigation.
 */
public class NavigationActivity extends AppCompatActivity {

  private ActivityNavigationBinding binding;
  private AppBarConfiguration appBarConfig;
  private NavController navController;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityNavigationBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    setupNavigation();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.navigation, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
    boolean handled = true;
    if (item.getItemId() == R.id.sign_out) {
      logout();
    } else if (item.getItemId() == R.id.preferences) {
      navController.navigate(R.id.navigation_settings);
    }
    else {
      handled = super.onOptionsItemSelected(item);
    }
    return handled;
  }

  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }

  private void logout() {
    GoogleSignInService.getInstance().signOut()
        .addOnCompleteListener((ignored) -> {
          Intent intent = new Intent(this, LoginActivity.class)
              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
          startActivity(intent);
        });
  }

  private void setupNavigation() {
    appBarConfig = new Builder(
        R.id.navigation_accelerometer, R.id.navigation_resort, R.id.navigation_profile)
        .build();
    navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig);
    NavigationUI.setupWithNavController(binding.navView, navController);
  }

}