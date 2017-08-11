package support.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.toped.app.newsped.BaseActivity;
import com.toped.app.newsped.R;
import com.toped.app.newsped.fragment.ArticleListFragment;
import com.toped.app.newsped.model.Source;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by arysuryawan on 8/8/17.
 */

public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.SourceViewHolder> {
    private final List<Source> sources;
    private final BaseActivity ac;

    public SourceAdapter(List<Source> sources, BaseActivity ac) {
        this.sources = sources;
        this.ac = ac;
    }

    @Override
    public SourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.row_source, parent, false);

        return new SourceViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(SourceViewHolder holder, int position) {
        Source source = (Source) sources.get(position);
        final String id = source.getId();
        final String name = source.getName();

        holder.sourceName.setText(source.getName());
        holder.cardItemSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ac.showFragment(new ArticleListFragment(id, name));
            }
        });
    }

    @Override
    public int getItemCount() {
        return sources.size();
    }


    public class SourceViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_source)
        CardView cardItemSource;
        @BindView(R.id.source_name)
        TextView sourceName;

        public SourceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
