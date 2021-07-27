package com.example.hw5_recyclerview;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<RowType> listItems;
    private Context mContext;


    public MyAdapter(List<RowType> listItems, Context mContext) {
        this.listItems = listItems;
        this.mContext = mContext;
    }

    View.OnClickListener clickListener;
    OnDeleteButtonItemClickListener deleteButtonListener;

    public void setOnItemClickListener(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setDeleteButtonListener(OnDeleteButtonItemClickListener  deleteButtonListener) {
        this.deleteButtonListener= deleteButtonListener;
    }

    public List<RowType> getData() {
        return listItems;
    }

    @Override
    public int getItemViewType(int position) {
        if (listItems.get(position) instanceof Basket) {
            return RowType.BASKET_ROW_TYPE;
        } else if (listItems.get(position) instanceof Apple) {
            return RowType.APPLE_ROW_TYPE;
        } else if (listItems.get(position) instanceof Sum) {
            return RowType.SUM_ROW_TYPE;
        } else if (listItems.get(position) instanceof BottomMenu) {
            return RowType.BOTTOM_MENU_ROW_TYPE;
        } else {
            return -1;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == RowType.BASKET_ROW_TYPE) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.basket, parent, false);
            return new BasketViewHolder(view);
        } else if (viewType == RowType.APPLE_ROW_TYPE) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.apple, parent, false);
            return new AppleViewHolder(view);
        } else if (viewType == RowType.SUM_ROW_TYPE) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.sum, parent, false);
            return new SumViewHolder(view);
        } else if (viewType == RowType.BOTTOM_MENU_ROW_TYPE) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bottom_menu, parent, false);
            return new BottomMenuViewHolder(view);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (holder instanceof AppleViewHolder) {
            if (((AppleViewHolder) holder).getDeleteButton()!= null) {
                int tagToSet;
                tagToSet = ((MainActivity)mContext).getItemIdByPosition(position);
                ((AppleViewHolder) holder).getDeleteButton().setTag(tagToSet);
                ((AppleViewHolder) holder).getDeleteButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int tag = (int) ((AppleViewHolder) holder).getDeleteButton().getTag();
                        ((MainActivity)mContext).deleteItemFromList(tag);
                    }
                });;

            }
        }
        if (holder instanceof BasketViewHolder) {
            if (((BasketViewHolder) holder).getAddButton()!= null) {
                int tagToSet = ((MainActivity)mContext).dataSet.get(position).id;
                ((BasketViewHolder) holder).getAddButton().setTag(tagToSet);
                ((BasketViewHolder) holder).getAddButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.v("CLICKED", "cc of item(" + position);
                        Log.v("mContext", "mContext = " + mContext);
                        int tag = (int) ((BasketViewHolder) holder).getAddButton().getTag();
                        ((MainActivity)mContext).addItemToList(tag);
                    }
                });;

            }
        }
        if (holder instanceof BottomMenuViewHolder) {
            if (((BottomMenuViewHolder) holder).getdeleteAllBasketsButton()!= null) {
                ((BottomMenuViewHolder) holder).getdeleteAllBasketsButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((MainActivity)mContext).deleteAllBaskets();
                    }
                });
            }
            if (((BottomMenuViewHolder) holder).getAddBasketButton()!= null) {
                ((BottomMenuViewHolder) holder).getAddBasketButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((MainActivity)mContext).addBasket();
                    }
                });
            }
        }
        if (holder instanceof SumViewHolder) {
            if (((SumViewHolder) holder).getSumTextView()!= null) {
                ((SumViewHolder) holder).getSumTextView().setText("Общее количество яблок: " + String.valueOf(((MainActivity)mContext).apllesCount));
            }

        }
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class BasketViewHolder extends ViewHolder {
        public BasketViewHolder(View itemView) {
            super(itemView);
        }

        public Button  getAddButton() {
            Button addButton = (Button) itemView.findViewById(R.id.add_button);
            return addButton;
        }
    }

    public static class AppleViewHolder extends ViewHolder {

        public AppleViewHolder(View itemView) {
            super(itemView);
        }

        public Button  getDeleteButton() {
            Button deleteButton = (Button) itemView.findViewById(R.id.delete_button);
            return deleteButton;
        }

    }

    public static class SumViewHolder extends ViewHolder {
        public SumViewHolder(View itemView) {
            super(itemView);
        }

        public TextView  getSumTextView() {
            TextView sumTextView = (TextView) itemView.findViewById(R.id.txtSum);
            return sumTextView;
        }
    }

    public static class BottomMenuViewHolder extends ViewHolder {
        public BottomMenuViewHolder(View itemView) {
            super(itemView);
        }

        public Button  getdeleteAllBasketsButton() {
            Button deleteAllBasketsButton = (Button) itemView.findViewById(R.id.deleteAllBaskets);
            return deleteAllBasketsButton;
        }

        public Button  getAddBasketButton() {
            Button addBasketButton = (Button) itemView.findViewById(R.id.addBasketButton);
            return addBasketButton;
        }
    }

    public interface OnDeleteButtonItemClickListener {
        void onDeleteIsClick(View button, int position);
    }
}
