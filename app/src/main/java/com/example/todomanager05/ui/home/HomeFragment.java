package com.example.todomanager05.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.example.todomanager05.R;
import com.example.todomanager05.databinding.FragmentHomeBinding;
import com.example.todomanager05.ui.OnItemClickListener;
import com.example.todomanager05.ui.TaskAdapter;
import com.example.todomanager05.ui.task.TaskModel;
import com.example.todomanager05.ui.utils.App;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements OnItemClickListener {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    TaskModel model;
    ArrayList<TaskModel> list = new ArrayList<>();
    Uri imageGet;
    NavController navController;

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
//        if (getArguments() != null) {
//            model = (TaskModel) getArguments().getSerializable(Constants.USER_TASK);
//            list.add(model);
//        }
        getDataFromDatabase();
        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.createFragment);

            }
        });

        initAdapter();
    }

    private ArrayList<TaskModel> getDataFromDatabase() {
        return (ArrayList<TaskModel>) App.getInstance().getDataBase().taskDao().getALL();
    }

    private void initAdapter() {
        TaskAdapter adapter = new TaskAdapter(getDataFromDatabase());
        binding.taskRecycler.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onLongClick(TaskModel taskModel) {
        AlertDialog alertDialog = new AlertDialog.Builder(requireActivity())
                .setTitle("вы точно хотите удалить?")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        App.getInstance().getDataBase().taskDao().delete(taskModel);

                    }
                })
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
        alertDialog.show();
        Log.e("ane", alertDialog.toString());
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