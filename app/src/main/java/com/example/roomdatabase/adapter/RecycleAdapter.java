package com.example.roomdatabase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabase.R;
import com.example.roomdatabase.room.Mahasiswa;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
    private Context mContext;
    private List<Mahasiswa> myList;
    OnclickRecycler onclickRecycler = this;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvNama)TextView nama;
        @BindView(R.id.tvNim)TextView nim;
        @BindView(R.id.tvKejuruan)TextView kejuruan;
        @BindView(R.id.tvAlamat)TextView alamat;

        public MyViewHolder(View v) {
            super(v);

            ButterKnife.bind(this,v);
        }
    }

    public RecycleAdapter(Context mContext, List<Mahasiswa> albumList) {
        this.mContext = mContext;
        this.myList = albumList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_mahasiswa, viewGroup, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final Mahasiswa album = myList.get(i);
        myViewHolder.tvNama.setText(album.getNama());
        myViewHolder.tvNim.setText(album.getNim());
        myViewHolder.tvKejuruan.setText(album.getKejuruan());
        myViewHolder.tvAlamat.setText(album.getAlamat());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                FragmentManager fm = ((Activity)mContext).getFragmentManager();
                UpdateDialog dialogFragment = new UpdateDialog(onclickRecycler);
                Bundle bundle = new Bundle();
                bundle.putInt("id",myList.get(position).getId());
                bundle.putInt("id_list",position);
                dialogFragment.setArguments(bundle);
                dialogFragment.show(fm," ");
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvAlamat;
        public TextView tvKejuruan;
        public TextView tvNama;
        public TextView tvNim;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAlamat = itemView.findViewById(R.id.tvAlamat);
            tvKejuruan = itemView.findViewById(R.id.tvKejuruan);
            tvNim = itemView.findViewById(R.id.tvNim);
            tvNama = itemView.findViewById(R.id.tvNama);
        }
    }
    @Override
    public void onItemDismiss(int position) {
        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setId(myList.get(position).getId());
        db.userDao().deleteUsers(mahasiswa);

        myList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void updateListener(int id, Mahasiswa mahasiswa) {
        myList.get(id).setAlamat(mahasiswa.getAlamat());
        myList.get(id).setKejuruan(mahasiswa.getKejuruan());
        myList.get(id).setNama(mahasiswa.getNama());
        myList.get(id).setNim(mahasiswa.getNim());
        notifyDataSetChanged();
    }


}
