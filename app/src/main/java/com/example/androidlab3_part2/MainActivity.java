package com.example.androidlab3_part2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.list)
    RecyclerView mList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        List<Film> films = new ArrayList<>();

        // putting data to list
        films.add(new Film("Герой СамСам",
                R.drawable.film1,
                "20.10.2020",
                "СамПланета в опасности! На родную обитель знаменитого СамСама напали Мрачуны — инопланетные жуки, которые распространяют по всей Вселенной… грусть.",
                "Украина"));
        films.add(new Film(
                "Капитан Саблезуб и сокровища Лама Рама",
                R.drawable.film2,
                "1.10.2021",
                "Увлекательные приключения отважного и грозного капитана Саблезуба, бороздящего моря и океаны в поисках несметных сокровищ. ",
                "Норвегия"));
        films.add(new Film(
                "Лесси. Возвращение домой",
                R.drawable.film3,
                "20.06.2019",
                "Двенадцатилетний Флориан и его колли Лесси — неразлучные друзья. Они счастливо живут в немецкой деревеньке. Когда отец Флориана лишается работы, семье приходится переезжать в дом поменьше, т. к. с собаками туда нельзя. ",
                "Франция"));
        films.add(new Film(
                "Ненасытная",
                R.drawable.film4,
                "05.01.2019",
                "Сериал посвящён тренеру Роберту Армстронгу (Бобу), который берёт запуганную девушку Пэтти своим клиентом. " +
                        "Девушку травят и даже избивают сверстники из-за её полноты. Она получает травму от удара в челюсть, и на время лечения ей стало необходимым держать рот закрытым[10]",
                "Албания"));

        //layout to have vertical manager
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mList.setLayoutManager(manager);

        // adding an adapter to list
        mList.setAdapter(new MyAdapter(films));

    }

    // class for list inputting
    class Film {

        private String name;
        private int avatarId;
        private String date;
        private String description;
        private String origin;

        public Film(String name, int avatarId, String date, String description, String origin) {
            this.name = name;
            this.avatarId = avatarId;
            this.date = date;
            this.description = description;
            this.origin = origin;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAvatarId() {
            return avatarId;
        }

        public void setAvatarId(int avatarId) {
            this.avatarId = avatarId;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }
    }

    // creating an adapter
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.FilmViewHolder>  {

        private List<Film> list;

        public MyAdapter(List<Film> list) {
            this.list = list;
        }

        //creating class ViewHolder

        public class FilmViewHolder extends RecyclerView.ViewHolder {

            public TextView title;
            public ImageView image;
            public TextView date;

            // put data to an item
            public FilmViewHolder(@NonNull View itemView) {
                super(itemView);

                title = itemView.findViewById(R.id.title);
                image = itemView.findViewById(R.id.imageView);
                date = itemView.findViewById(R.id.date);

            }
        }

        @NonNull
        @Override
        public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);

            FilmViewHolder holder = new FilmViewHolder(v);

            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
            holder.title.setText(list.get(position).getName());
            holder.image.setImageResource(list.get(position).getAvatarId());
            holder.date.setText(list.get(position).getDate());
            holder.itemView.setOnClickListener(view -> {

                // adding data that  has to go to another activity
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("title",list.get(position).getName());
                intent.putExtra("date", list.get(position).getDate());
                intent.putExtra("origin", list.get(position).getOrigin());
                intent.putExtra("description", list.get(position).getDescription());
                intent.putExtra("image", list.get(position).getAvatarId());

                startActivity(intent);
            });
        }


        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}

