package com.example.ajcin.flashbackmusicteam16;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;


public class AlbumFragment extends ListFragment {


    private OnFragmentInteractionListener mListener;
    String[] album_list_string;
    private PopulateMusic populateMusic;

    public AlbumFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        album_list_string = bundle.getStringArray("albums");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, album_list_string);
        setListAdapter(adapter);
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_album, container, false);
         view.findViewById(android.R.id.list).setScrollContainer(true);
         return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        AlbumView album_view = new AlbumView();
        populateMusic = album_view.getPopulateMusic();
        Album selected_album = populateMusic.getAlbum(album_list_string[position]);
        String[] album_song_list = populateMusic.getSongListInAlbumString(selected_album);
        Bundle album_song_bundle = new Bundle();
        album_song_bundle.putStringArray("album_songs",album_song_list);
        albumsongsFragment album_songs_fragment = new albumsongsFragment();
        album_songs_fragment.setArguments(album_song_bundle);
        transaction.replace(R.id.musicItems,album_songs_fragment).commit();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            //throw new RuntimeException(context.toString()
                   // + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
