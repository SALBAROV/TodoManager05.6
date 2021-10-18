package com.example.todomanager05.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.todomanager05.R;
import com.example.todomanager05.databinding.FragmentHomeBinding;
import com.example.todomanager05.ui.TaskAdapter;
import com.example.todomanager05.ui.task.TaskModel;
import com.example.todomanager05.ui.utils.Constants;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    TaskModel model;
    ArrayList<TaskModel> list = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            model = (TaskModel) getArguments().getSerializable(Constants.USER_TASK);
            list.add(model);
        }
        initAdapter();
    }

    private void initAdapter() {
        TaskAdapter adapter = new TaskAdapter(list);
        binding.taskRecycler.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}


//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//       if (getArguments() != null){
//           String s = getArguments().getString(Constants.USER_TASK);
//           binding.tvTitle.setText(s);
//       }
//        return root;
//    }
//
//    @Override
//    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        String userTask="";
//        Log.e("ololo","onViewCreated:"+userTask);
//    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
//}