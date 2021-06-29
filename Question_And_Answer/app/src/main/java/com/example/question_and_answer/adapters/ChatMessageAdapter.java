package com.example.question_and_answer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.question_and_answer.R;
import com.example.question_and_answer.base.BaseViewHolder;
import com.example.question_and_answer.common.Define;
import com.example.question_and_answer.models.ChatMessage;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageAdapter.ViewHolder> {

    private static final int SYSTEM_VIEW = 0;
    private static final int SELF_VIEW = 1;
    private static final int RECEIVED_VIEW = 2;

    Context mContext;
    ArrayList<ChatMessage> mData;


    public ChatMessageAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addItems(ChatMessage item) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.add(item);
        notifyDataSetChanged();
    }

    public class ViewHolder extends BaseViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class SystemHolder extends ViewHolder {
        @BindView(R.id.messageTextView1)
        AppCompatTextView messageTextView1;

        public SystemHolder(View itemView) {
            super(itemView);
        }
    }

    public class SelfHolder extends ViewHolder {
        @BindView(R.id.messageTextView2)
        AppCompatTextView messageTextView2;

        public SelfHolder(View itemView) {
            super(itemView);
        }
    }

    public class ReceivedHolder extends ViewHolder {
        @BindView(R.id.messageTextView3)
        AppCompatTextView messageTextView3;

        @BindView(R.id.messageOwnerTextView)
        AppCompatTextView messageOwnerTextView;

        public ReceivedHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage currentItem = mData.get(position);
        String messageType = currentItem.getMessageType();

        if (messageType.equals(Define.MESSAGE_TYPE_SYSTEM)) {
            return SYSTEM_VIEW;
        } else if (messageType.equals(Define.MESSAGE_TYPE_SELF)) {
            return SELF_VIEW;
        } else if (messageType.equals(Define.MESSAGE_TYPE_RECEIVE)) {
            return RECEIVED_VIEW;
        }

        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = null;

        switch (viewType) {
            case SYSTEM_VIEW:
                view = inflater.inflate(R.layout.item_system_message, parent, false);
                return new SystemHolder(view);
            case SELF_VIEW:
                view = inflater.inflate(R.layout.item_self_message, parent, false);
                return new SelfHolder(view);
            case RECEIVED_VIEW:
                view = inflater.inflate(R.layout.item_received_message, parent, false);
                return new ReceivedHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        ChatMessage item = mData.get(position);

        if (viewType == SYSTEM_VIEW) {
            SystemHolder systemHolder = (SystemHolder) holder;
            String messageSuffix = item.getUserAction().equals("entered") ? "님이 채팅방에 들어왔습니다." : "님이 채팅방을 나갔습니다.";

//            Toast.makeText(mContext,item.getMessageOwner() + messageSuffix,Toast.LENGTH_SHORT).show();
            systemHolder.messageTextView1.setText(item.getMessageOwner() + messageSuffix);
        } else if (viewType == SELF_VIEW) {
            SelfHolder selfHolder = (SelfHolder) holder;

//            Toast.makeText(mContext,item.getMessageContent(),Toast.LENGTH_SHORT).show();
            selfHolder.messageTextView2.setText(item.getMessageContent());
        } else if (viewType == RECEIVED_VIEW) {
            ReceivedHolder receivedHolder = (ReceivedHolder) holder;

//            Toast.makeText(mContext,item.getMessageOwner()+item.getMessageContent(),Toast.LENGTH_SHORT).show();

            receivedHolder.messageOwnerTextView.setText(item.getMessageOwner());
            receivedHolder.messageTextView3.setText(item.getMessageContent());
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
