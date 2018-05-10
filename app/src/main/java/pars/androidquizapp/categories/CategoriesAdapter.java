package pars.androidquizapp.categories;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pars.androidquizapp.R;
import pars.androidquizapp.data.Category;

/**
 * Created by Princess on 15/04/2018.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {

    private Context context;
    private List<Category> categoryList;
    private OnCategoryClicked onCategoryClicked;


    public CategoriesAdapter(Context context, List<Category> categoryList, OnCategoryClicked onCategoryClicked){
        this.context = context;
        this.categoryList = categoryList;
        this.onCategoryClicked = onCategoryClicked;
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
        Category result = categoryList.get(position);
        holder.categoryTitle.setText(result.getCategory());
        //holder.categoryId.setText(String.valueOf(result.getId())+ ".");
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }


    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.relative)
        RelativeLayout relativeLayout;
        @BindView(R.id.category_title)
        TextView categoryTitle;
        @BindView(R.id.question_count)
        TextView questionCount;
        @BindView(R.id.play_button)
        Button playButton;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCategoryClicked.onClick(getAdapterPosition());
        }
    }

    public interface OnCategoryClicked {
        //void onClick(Category data);
        void onClick(int position);
    }

}
