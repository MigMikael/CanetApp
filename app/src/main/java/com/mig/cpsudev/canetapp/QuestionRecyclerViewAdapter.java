package com.mig.cpsudev.canetapp;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class QuestionRecyclerViewAdapter extends RecyclerView.Adapter<QuestionRecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> mData;
    private Context mContext;

    public QuestionRecyclerViewAdapter(ArrayList<String> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public QuestionRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_card, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(QuestionRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.question.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView question;
        private TextView choice1;
        private TextView choice2;
        private TextView choice3;
        private TextView choice4;

        public ViewHolder(View itemView) {
            super(itemView);
            question = (TextView) itemView.findViewById(R.id.questionTextView);
            choice1 = (TextView) itemView.findViewById(R.id.choice1TextView);
            choice2 = (TextView) itemView.findViewById(R.id.choice2TextView);
            choice3 = (TextView) itemView.findViewById(R.id.choice3TextView);
            choice4 = (TextView) itemView.findViewById(R.id.choice4TextView);

            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            question.setBackgroundColor(color);
        }
    }
}
