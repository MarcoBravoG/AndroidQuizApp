package pars.androidquizapp.categories;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import pars.androidquizapp.R;
import pars.androidquizapp.data.Category;
import pars.androidquizapp.data.Question;
import pars.androidquizapp.questions.QuestionsContract;


public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {

    private Context context;
    private List<Category> categoryList;
    private OnCategoryClicked onCategoryClicked;
    private OnCategoryOnLongClicked onLongClicked;
    private int color;


    public CategoriesAdapter(Context context, List<Category> categoryList,
                             OnCategoryClicked onCategoryClicked, OnCategoryOnLongClicked onLongClicked){
        this.context = context;
        this.categoryList = categoryList;
        this.onCategoryClicked = onCategoryClicked;
        this.onLongClicked = onLongClicked;
    }

    public void randomColor(){
        Random random = new Random();
        color = Color.argb(255, random.nextInt(256),
                random.nextInt(256), random.nextInt(256));
    }


    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_layout, parent, false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        randomColor();
        Category result = categoryList.get(position);
        holder.categoryTitle.setText(result.getCategory());
        holder.relativeLayout.setBackgroundColor(color);

        holder.playButton.setOnClickListener(v -> {
            onCategoryClicked.onPlayButtonClicked(result);
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public Category getItem(int position){
        return categoryList.get(position);
    }

    public void setValues(List<Category> values){
        categoryList = values;
        notifyDataSetChanged();
    }


    /**
    * ViewHolder class
     **/
    public class CategoryViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        Category category;
        long categoryId;

        @BindView(R.id.relative)
        RelativeLayout relativeLayout;
        @BindView(R.id.category_title)
        TextView categoryTitle;
        @BindView(R.id.play_button)
        Button playButton;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            category = getItem(position);
            categoryId = category.getId();
            onCategoryClicked.onCategoryClick(categoryId);
        }


        @Override
        public boolean onLongClick(View v) {
            int position = getAdapterPosition();
            category = getItem(position);
            onLongClicked.onCategoryLongClick(category);
            return false;
        }
    }

    public interface OnCategoryClicked {
        void onCategoryClick(long categoryId);

        void onPlayButtonClicked(Category category);
    }

    public interface OnCategoryOnLongClicked {

        void onCategoryLongClick(Category category);
    }


}
