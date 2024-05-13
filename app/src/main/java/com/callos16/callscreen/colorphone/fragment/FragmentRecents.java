package com.callos16.callscreen.colorphone.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telecom.PhoneAccountHandle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aitsuki.swipe.SwipeMenuRecyclerView;
import com.callos16.callscreen.colorphone.ActivityHome;
import com.callos16.callscreen.colorphone.R;
import com.callos16.callscreen.colorphone.adapter.AdapterRecent;
import com.callos16.callscreen.colorphone.custom.TextW;
import com.callos16.callscreen.colorphone.custom.ViewModeRecent;
import com.callos16.callscreen.colorphone.dialog.DialogAddFav;
import com.callos16.callscreen.colorphone.dialog.DialogNotification;
import com.callos16.callscreen.colorphone.dialog.DialogResult;
import com.callos16.callscreen.colorphone.dialog.FavResult;
import com.callos16.callscreen.colorphone.item.ItemContact;
import com.callos16.callscreen.colorphone.item.ItemFavorites;
import com.callos16.callscreen.colorphone.item.ItemRecentGroup;
import com.callos16.callscreen.colorphone.item.ItemSimInfo;
import com.callos16.callscreen.colorphone.utils.MyShare;
import com.callos16.callscreen.colorphone.utils.OtherUtils;
import com.callos16.callscreen.colorphone.utils.ReadContact;
import com.callos16.callscreen.colorphone.utils.SimUtils;


import java.util.ArrayList;


public class FragmentRecents extends BaseFragment {
    private ViewFragmentRecents viewFragmentRecents;

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.viewFragmentRecents == null) {
            this.viewFragmentRecents = new ViewFragmentRecents(layoutInflater.getContext());
        }
        return this.viewFragmentRecents;
    }

    @Override 
    public void onResume() {
        super.onResume();
        ViewFragmentRecents viewFragmentRecents = this.viewFragmentRecents;
        if (viewFragmentRecents != null) {
            viewFragmentRecents.loadAllRecent();
        }
    }



    public class ViewFragmentRecents extends RelativeLayout implements AdapterRecent.RecentItemClick {
        private final AdapterRecent adapterRecent;
        private final ArrayList<ItemRecentGroup> arrRecent;
        private final ArrayList<ItemSimInfo> arrSim;
        private final int posSim;
        private final boolean theme;
        private final TextW tvEdit;
        private final TextW tvEmpty;
        private final TextW tvRemoveAll;

        public ViewFragmentRecents(Context context) {
            super(context);
            ArrayList<ItemSimInfo> availableSIMCardLabels = SimUtils.getAvailableSIMCardLabels(context);
            this.arrSim = availableSIMCardLabels;
            this.posSim = MyShare.getPosSim(context);
            int widthScreen = OtherUtils.getWidthScreen(context) / 25;
            boolean theme = MyShare.getTheme(context);
            this.theme = theme;
            ArrayList<ItemRecentGroup> arrayList = new ArrayList<>();
            this.arrRecent = arrayList;
            AdapterRecent adapterRecent = new AdapterRecent(arrayList, availableSIMCardLabels, theme, this);
            this.adapterRecent = adapterRecent;
            TextW textW = new TextW(context);
            this.tvEdit = textW;
            textW.setId(100);
            textW.setTextColor(Color.parseColor("#007AFF"));
            textW.setPadding(widthScreen, widthScreen, widthScreen, widthScreen);
            textW.setOnClickListener(new OnClickListener() { 
                @Override 
                public final void onClick(View view) {
                    ViewFragmentRecents.this.m149x69848d70(view);
                }
            });
            LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.addRule(21);
            layoutParams.setMargins(0, MyShare.getSizeNotification(context), 0, 0);
            addView(textW, layoutParams);
            TextW textW2 = new TextW(context);
            this.tvRemoveAll = textW2;
            textW2.setId(654982);
            textW2.setTextColor(Color.parseColor("#007AFF"));
            textW2.setupText(400, 4.0f);
            textW2.setPadding(widthScreen, widthScreen, widthScreen, widthScreen);
            textW2.setText(R.string.delete);
            textW2.setOnClickListener(new OnClickListener() { 
                @Override 
                public final void onClick(View view) {
                    ViewFragmentRecents.this.m150x49fde371(view);
                }
            });
            LayoutParams layoutParams2 = new LayoutParams(-2, -2);
            layoutParams2.setMargins(0, MyShare.getSizeNotification(context), 0, 0);
            addView(textW2, layoutParams2);
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setGravity(17);
            LayoutParams layoutParams3 = new LayoutParams(-1, -1);
            layoutParams3.addRule(6, textW.getId());
            layoutParams3.addRule(8, textW.getId());
            addView(linearLayout, layoutParams3);
            ViewModeRecent viewModeRecent = new ViewModeRecent(context);
            viewModeRecent.setModeResult(new ViewModeRecent.ModeResult() { 
                @Override 
                public final void onMode(boolean z) {
                    ViewFragmentRecents.this.m151x2a773972(z);
                }
            });
            linearLayout.addView(viewModeRecent, -2, -2);
            TextW textW3 = new TextW(context);
            this.tvEmpty = textW3;
            textW3.setupText(400, 3.8f);
            textW3.setText(R.string.empty_recent);
            textW3.setGravity(17);
            textW3.setTextColor(Color.parseColor("#aaaaaa"));
            textW3.setVisibility(View.GONE);
            LayoutParams layoutParams4 = new LayoutParams(-1, -1);
            layoutParams4.setMargins(widthScreen, 0, widthScreen, 0);
            layoutParams4.addRule(3, textW.getId());
            addView(textW3, layoutParams4);
            SwipeMenuRecyclerView swipeMenuRecyclerView = new SwipeMenuRecyclerView(context);
            swipeMenuRecyclerView.setAdapter(adapterRecent);
            swipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
            LayoutParams layoutParams5 = new LayoutParams(-1, -1);
            layoutParams5.addRule(3, textW.getId());
            addView(swipeMenuRecyclerView, layoutParams5);
            if (theme) {
                setBackgroundColor(-1);
            } else {
                setBackgroundColor(Color.parseColor("#2C2C2C"));
            }
            updateEdit();
        }



        public  void m149x69848d70(View view) {
            AdapterRecent adapterRecent = this.adapterRecent;
            adapterRecent.setChoose(!adapterRecent.isChoose());
            updateEdit();
        }



        public  void m150x49fde371(View view) {
            onRemoveAll();
        }



        public  void m151x2a773972(boolean z) {
            this.adapterRecent.showMiss(!z);
        }

        private void updateEdit() {
            if (this.adapterRecent.isChoose()) {
                this.tvEdit.setText(R.string.done);
                this.tvRemoveAll.setVisibility(View.VISIBLE);
                this.tvEdit.setupText(600, 4.0f);
                return;
            }
            this.tvEdit.setText(R.string.edit);
            this.tvRemoveAll.setVisibility(View.GONE);
            this.tvEdit.setupText(400, 4.0f);
        }

        private void checkList() {
            if (this.arrRecent.isEmpty()) {
                this.tvEmpty.setVisibility(View.VISIBLE);
                this.tvEdit.setVisibility(View.INVISIBLE);
                this.tvRemoveAll.setVisibility(View.GONE);
                if (this.adapterRecent.isChoose()) {
                    this.adapterRecent.setChoose(false);
                    updateEdit();
                    return;
                }
                return;
            }
            this.tvEmpty.setVisibility(View.GONE);
            this.tvEdit.setVisibility(View.VISIBLE);
        }


        public void loadAllRecent() {
            final Handler handler = new Handler(new Handler.Callback() { 
                @Override 
                public final boolean handleMessage(Message message) {
                    return ViewFragmentRecents.this.m147x64a531a9(message);
                }
            });
            new Thread(new Runnable() { 
                @Override 
                public final void run() {
                    ViewFragmentRecents.this.m148x451e87aa(handler);
                }
            }).start();
        }



        public  boolean m147x64a531a9(Message message) {
            checkList();
            this.adapterRecent.addNewData();
            return true;
        }



        public  void m148x451e87aa(Handler handler) {
            this.arrRecent.clear();
            this.arrRecent.addAll(ReadContact.getAllRecents(getContext()));
            handler.sendEmptyMessage(1);
        }

        @Override 
        public void onLongClick(final ItemRecentGroup itemRecentGroup) {
            ItemContact contactWithNumber = ReadContact.getContactWithNumber(getContext(), itemRecentGroup.arrRecent.get(0).number);
            if (contactWithNumber == null) {
                contactWithNumber = new ItemContact(itemRecentGroup.arrRecent.get(0).number);
            }
            new DialogAddFav(getContext(), this.theme, false, contactWithNumber, new FavResult() { 
                @Override 
                public final void onFavResult(ItemFavorites itemFavorites) {
                    ViewFragmentRecents.this.m152xf95ee2(itemRecentGroup, itemFavorites);
                }
            }).show();
        }



        public  void m152xf95ee2(ItemRecentGroup itemRecentGroup, ItemFavorites itemFavorites) {
            if (itemFavorites.type == 0) {
                OtherUtils.sendMessage(getContext(), itemFavorites.number);
            } else {
                onItemClick(itemRecentGroup);
            }
        }

        @Override 
        public void onItemClick(ItemRecentGroup itemRecentGroup) {
            PhoneAccountHandle phoneAccountHandle;
            if (this.arrSim.size() > 0) {
                if (this.posSim < this.arrSim.size()) {
                    phoneAccountHandle = this.arrSim.get(this.posSim).handle;
                } else {
                    phoneAccountHandle = this.arrSim.get(0).handle;
                }
                OtherUtils.call(getContext(), itemRecentGroup.arrRecent.get(0).number, phoneAccountHandle);
            }
        }

        @Override 
        public void onDel(ItemRecentGroup itemRecentGroup) {
            if (!OtherUtils.checkPer(getContext(), "android.permission.WRITE_CALL_LOG")) {
                FragmentRecents.this.requestPermissions(new String[]{"android.permission.WRITE_CALL_LOG"}, 1);
                return;
            }
            String[] strArr = new String[itemRecentGroup.arrRecent.size()];
            for (int i = 0; i < itemRecentGroup.arrRecent.size(); i++) {
                strArr[i] = itemRecentGroup.arrRecent.get(i).id;
            }
            ReadContact.removeRecents(getContext(), strArr);
            this.adapterRecent.removeRecent(itemRecentGroup);
            checkList();
        }

        @Override 
        public void onInfo(ItemRecentGroup itemRecentGroup) {
            if (FragmentRecents.this.getActivity() instanceof ActivityHome) {
                ItemContact contactWithNumber = ReadContact.getContactWithNumber(getContext(), itemRecentGroup.arrRecent.get(0).number);
                if (contactWithNumber != null) {
                    FragmentInfo newInstance = FragmentInfo.newInstance(contactWithNumber, itemRecentGroup, R.string.recents);
                    newInstance.setContactResult(FragmentRecents.this.contactResult);
                    ((ActivityHome) FragmentRecents.this.getActivity()).showFragment(newInstance, true);
                    return;
                }
                FragmentInfoAnother newInstance2 = FragmentInfoAnother.newInstance(itemRecentGroup);
                newInstance2.setContactResult(FragmentRecents.this.contactResult);
                ((ActivityHome) FragmentRecents.this.getActivity()).showFragment(newInstance2, true);
            }
        }

        private void onRemoveAll() {
            new DialogNotification(getContext(), R.string.delete_all_recents, R.string.delete_up, this.theme, new DialogResult() { 
                @Override 
                public final void onActionClick() {
                    ViewFragmentRecents.this.m153x10def8b4();
                }
            }).show();
        }



        public  void m153x10def8b4() {
            ReadContact.removeAllRecents(getContext());
            this.adapterRecent.removeAll();
            checkList();
            updateEdit();
        }
    }
}
