package com.example.todomanager05.ui.task;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.todomanager05.R;
import com.example.todomanager05.databinding.FragmentCreateBinding;
import com.example.todomanager05.ui.utils.Constants;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Calendar;

public class CreateFragment extends Fragment {
    private FragmentCreateBinding binding;
    String userTask;
    String userChoosedDate;
    String time;

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
    public void onViewCreated(@NotNull View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
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
                TaskModel model = new TaskModel(R.color.purple_200,userTask,userChoosedDate +"/"+time,"https://static.wikia.nocookie.net/naruto/images/d/dd/Naruto_Uzumaki%21%21.png/revision/latest?cb=20170816203155&path-prefix=ru");
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.USER_TASK, model);
                navController.navigate(R.id.nav_home, bundle);
            }
        });
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
                        time = hourOfDay+" : "+minute;
                        userChoosedDate= date.get(Calendar.MONTH)+"." +date.get(Calendar.DAY_OF_MONTH);

                        Log.v("ololo", "The choosen one " + date.getTime());
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

