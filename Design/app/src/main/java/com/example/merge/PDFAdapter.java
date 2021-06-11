package com.example.merge;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class PDFAdapter extends FirebaseRecyclerAdapter<PDFHelperClass,PDFAdapter.viewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PDFAdapter(@NonNull FirebaseRecyclerOptions<PDFHelperClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int i, @NonNull PDFHelperClass pdfHelperClass) {
        holder.title.setText(pdfHelperClass.getYear());
        holder.imageView.setText(pdfHelperClass.getTitle());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.imageView.getContext(),ViewPDFActivity.class);
                intent.putExtra("PDF",pdfHelperClass.getPDF());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.imageView.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view,parent,false);
        return new viewHolder(v);
    }

    class viewHolder extends RecyclerView.ViewHolder{
        TextView title, imageView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.pdf);
            title = itemView.findViewById(R.id.title);
        }
    }
}
