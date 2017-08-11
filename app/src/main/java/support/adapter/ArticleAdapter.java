package support.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.toped.app.newsped.ArticleDetailActivity;
import com.toped.app.newsped.BaseActivity;
import com.toped.app.newsped.R;
import com.toped.app.newsped.model.Article;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by arysuryawan on 8/8/17.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    private final List<Article> articles;
    private final BaseActivity ac;

    public ArticleAdapter(List<Article> articles, BaseActivity ac) {
        this.articles = articles;
        this.ac = ac;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.row_article, parent, false);

        return new ArticleViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        Article article = (Article) articles.get(position);
        final String url = article.getUrl();

        holder.articleTitle.setText(article.getTitle());
        holder.cardItemArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ac, ArticleDetailActivity.class);
                intent.putExtra("article_url", url);
                ac.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }


    public class ArticleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_article)
        CardView cardItemArticle;
        @BindView(R.id.article_title)
        TextView articleTitle;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
