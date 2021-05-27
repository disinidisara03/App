package com.firstapp.ceylonhearts;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;

public class RegisterActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private RecyclerView postList;
    DatabaseReference PostRef;
    Button donate;
    Button fundraiser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        fundraiser = (Button) findViewById(R.id.btn_fund);
        donate = (Button) findViewById(R.id.donation);


        /*fundraiser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToAuth();
            }
        });*/

        fundraiser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToAuth();
            }
        });


        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToPayment();
            }
        });

    }

    private void sendToAuth() {

        Intent auth = new Intent(RegisterActivity.this,AuthenticateActivity.class);
        startActivity(auth);

    }

    private void sendToPayment() {
        Intent pay = new Intent(RegisterActivity.this,PaymentActivity.class);
        startActivity(pay);
    }

/*//Mujeeb Last
private void UserMenuSelector(MenuItem item) {
    switch (item.getItemId()){
        case R.id.nav_home:
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
            break;

        case R.id.fund:
            AunthenticateUser();
            break;

        case R.id.Contact_Us:
            Toast.makeText(this, "Conatct Us", Toast.LENGTH_SHORT).show();
            break;

        case R.id.Payment:
            Toast.makeText(this, "Payment Method", Toast.LENGTH_SHORT).show();
            break;

        case R.id.logout:
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
            break;
    }
}*/



   /* @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void UserMenuSelector(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                break;

            case R.id.fund:
                sendusertopostactvity();
                break;

            case R.id.Contact_Us:
                Toast.makeText(this, "Conatct Us", Toast.LENGTH_SHORT).show();
                break;

            case R.id.Payment:
                Toast.makeText(this, "Payment Method", Toast.LENGTH_SHORT).show();
                break;

            case R.id.logout:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                break;
        }
    }





    private void sendusertologinactivity() {
        Intent LoginIntent = new Intent(RegisterActivity.this, RegisterActivity.class);
        LoginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(LoginIntent);
        finish();
    }


    @Override
    protected void onStart() {
        super.onStart();


        FirebaseRecyclerOptions<Posts> options = new
                FirebaseRecyclerOptions.Builder<Posts>()
                .setQuery(PostRef, Posts.class)
                .build();

        FirebaseRecyclerAdapter<Posts, PostsViewHolder> adapter = new
                FirebaseRecyclerAdapter<Posts, PostsViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull PostsViewHolder holder, int position, @NonNull Posts model) {
                        holder.desc.setText(model.getDescription());
                        holder.username.setText(model.getUsername());
                        Picasso.get().load(model.getCharity_image()).into(holder.posts);

                    }

                    @NonNull
                    @Override
                    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_charities_layout, parent, false);
                         PostsViewHolder viewHolder = new PostsViewHolder(view);
                        return viewHolder;
                    }
                };
    }


    public static class PostsViewHolder extends RecyclerView.ViewHolder
    {
        ImageView posts;
        EditText desc;
        TextView username;
        View mView;

        public PostsViewHolder(@NonNull View itemView)
        {
            super(itemView);

            posts =itemView.findViewById(R.id.image_view);
            desc = itemView.findViewById(R.id.description);
            username = itemView.findViewById(R.id.username);
        }

        public void setUsername(String  username){
            TextView fullname = itemView.findViewById(R.id.username);
        }

        public void setCharity_image(String charity_image){

        }
    }
*/




}







