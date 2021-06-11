package com.example.merge;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class NotificationAdaptor extends RecyclerView.Adapter<NotificationAdaptor.MyViewHolder> {
    private ShowNotification activity;
    private List<NotificationModel> mList;
    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();

    public NotificationAdaptor(ShowNotification activity, List<NotificationModel>mList){
        this.activity=activity;
        this.mList =mList;
    }
    public void deleteData(int position){
        NotificationModel item = mList.get(position);
        fStore.collection("Notification").document(item.getYear()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            notifyRemoved(position);
                            Toast.makeText(activity,"Notification data are delete",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(activity,"error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void notifyRemoved(int position){

        mList.remove(position);
        notifyItemRemoved(position);
        activity.showNotification();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.item_notification,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.year.setText(mList.get(position).getYear());
        holder.category.setText(mList.get(position).getCategory());
        holder.subject.setText(mList.get(position).getSubject());
        holder.description.setText(mList.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static  class MyViewHolder  extends RecyclerView.ViewHolder{

        TextView year, category, subject, description;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            year=itemView.findViewById(R.id.txt_Year);
            subject=itemView.findViewById(R.id.txt_Subject);
            category=itemView.findViewById(R.id.txt_Category);
            description=itemView.findViewById(R.id.txt_Description);


        }
    }
}
