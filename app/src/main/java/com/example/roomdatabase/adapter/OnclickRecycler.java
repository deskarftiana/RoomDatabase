package com.example.roomdatabase.adapter;

import com.example.roomdatabase.room.Mahasiswa;

public interface OnclickRecycler {
    void onItemDismiss(int position);
    void updateListener(int id, Mahasiswa mahasiswa);
}
