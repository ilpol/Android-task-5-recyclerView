package com.example.hw5_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter adapter;

    public int apllesCount = 0;
    private int  numBaskets = 0;
    private int maxApples = 3;

    public List<RowType> dataSet = new ArrayList<>();
    private Map<Integer, Integer> apllesInBaskets = new HashMap<>();


    public void deleteAllBaskets() {
        for (ListIterator<RowType> iter = dataSet.listIterator(); iter.hasNext(); ) {
            RowType item = iter.next();
            if (dataSet.size() > 2) {
                iter.remove();
            }
        }
        apllesInBaskets.clear();
        numBaskets = 0;
        apllesCount = 0;
        adapter.notifyDataSetChanged();
    }


    public void addBasket () {
        dataSet.add(0, new Basket("Корзина", numBaskets));
        apllesInBaskets.put(numBaskets, 0);
        numBaskets +=1;
        adapter.notifyDataSetChanged();
    }

    private int getBasketPositionByTag(int tag) {
        int position = 0;
        for (ListIterator<RowType> iter = dataSet.listIterator(); iter.hasNext(); ) {
            RowType item = iter.next();
            if (item instanceof Basket && ((Basket) item).id == tag) {
                return position;
            } else {
                position += 1;
            }
        }
        return position;
    }

    public void addItemToList(Integer tag) {
        if (apllesInBaskets.get(tag) >= maxApples) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Нельзя быть жадным", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            apllesCount += 1;
            int positionToSet = getBasketPositionByTag(tag);
            dataSet.add(positionToSet + 1,new Apple("Яблоко", apllesCount, tag));
            apllesInBaskets.put(tag, apllesInBaskets.get(tag) + 1);
            adapter.notifyDataSetChanged();
        }
    }

    public void deleteItemFromList(int id) {
        for (ListIterator<RowType> iter = dataSet.listIterator(); iter.hasNext(); ) {
            RowType item = iter.next();
            if (item instanceof Apple && ((Apple) item).id == id) {
                iter.remove();
                apllesCount -= 1;
                apllesInBaskets.put(((Apple) item).basketId,  apllesInBaskets.get(((Apple) item).basketId) - 1);
            }
        }
        adapter.notifyDataSetChanged();
    }

    public Integer getItemIdByPosition(int pos) {
        Integer elem = dataSet.get(pos).id;
        return elem;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);


        //Initial Data
        dataSet.add(new Basket("Корзина", 0));
        dataSet.add(new Apple("Яблоко", 1, 0));
        dataSet.add(new Basket("Корзина", 1));
        dataSet.add(new Apple("Яблоко", 3, 1));
        dataSet.add(new Sum("Сумма", 4));
        dataSet.add(new BottomMenu());

        apllesCount = 2;
        numBaskets = 2;
        apllesInBaskets.put(0, 1);
        apllesInBaskets.put(1, 1);

        adapter = new MyAdapter(dataSet, this);

        recyclerView.setAdapter(adapter);
    }

}

