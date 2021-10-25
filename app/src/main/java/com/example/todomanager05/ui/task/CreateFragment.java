package com.example.todomanager05.ui.task;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Gallery;
import android.widget.TimePicker;

import com.bumptech.glide.Glide;
import com.example.todomanager05.R;
import com.example.todomanager05.databinding.FragmentCreateBinding;
import com.example.todomanager05.ui.home.HomeFragment;
import com.example.todomanager05.ui.utils.App;
import com.example.todomanager05.ui.utils.Constants;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

public class CreateFragment extends Fragment {
    String userTask;
    String userChoosedDate;
    String time;
    String image;
    FragmentCreateBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        addGallery();
        binding.setTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimePicker();
            }
        });
        binding.applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userTask = binding.taskEd.getText().toString();

                TaskModel model = new TaskModel(R.color.purple_200, userTask, userChoosedDate + "/" + time,
                        image);
//               Bundle bundle = new Bundle();
  //             Log.e("anime", "onClick: "+model.image );
//               bundle.putSerializable(Constants.USER_TASK, model);

                App.getInstance().getDataBase().taskDao().insert(model);
                App.getInstance().showToast("Hello World");
                navController.navigate(R.id.nav_home);
            }
        });
//        passImage();
    }

    public void showDateTimePicker() {
        final Calendar currentDate = Calendar.getInstance();

        Calendar date = Calendar.getInstance();
        new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date.set(year, monthOfYear, dayOfMonth);
                new TimePickerDialog(requireContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        date.set(Calendar.MINUTE, minute);
                        time = hourOfDay + " : " + minute;
                        userChoosedDate = date.get(Calendar.MONTH) + "." + date.get(Calendar.DAY_OF_MONTH);

                        Log.v("ololo", "The choosen one " + date.getTime());
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    if (uri != null) {
                        image = uri.toString();
                        Glide.with(binding.myImage).load(image).into(binding.myImage);
                    }
                    // binding.myImage.setImageURI(uri);
                }
            });

    private void addGallery() {
        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetContent.launch("image/*");
            }
        });
    }

//    private void passImage() {
//        Bundle bundle = new Bundle();
//        bundle.putString("image", String.valueOf(binding.myImage));
//        HomeFragment homeFragment = new HomeFragment();
//        homeFragment.setArguments(bundle);
//        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
//        navController.navigate(R.id.nav_home, bundle);
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}