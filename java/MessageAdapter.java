package wafoot.becoming.wafoot;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageHolder> {

    private Context context;
    private List<String> list;

    private String afterColon;
    private String textToCopy;

    public MessageAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    protected static class MessageHolder extends RecyclerView.ViewHolder {
        private TextView tvMessage;
        private MaterialCardView cvMessage;

        public MessageHolder(View view) {
            super(view);
            tvMessage = view.findViewById(R.id.tvMessage);
            cvMessage = view.findViewById(R.id.cvMessage);
        }
    }

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.messages, parent, false);
        return new MessageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {
        String message = list.get(position);
        int indexOfColon = message.indexOf(":");

        if (indexOfColon != -1) {
            afterColon = message.substring(indexOfColon + 1).trim();
        }

        if (afterColon.equals("")) {
            holder.cvMessage.setVisibility(View.GONE);
        }

        else {
            holder.tvMessage.setText(list.get(position));
        }

        holder.cvMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                String [] items = {context.getString(R.string.copy_message)};

                try {
                    alertDialogBuilder.setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String originalTextToCopy = holder.tvMessage.getText().toString();
                            int indexOfColon1 = originalTextToCopy.indexOf(":");

                            if (indexOfColon1 != -1) {
                                textToCopy = originalTextToCopy.substring(indexOfColon1 + 2);
                            }

                            ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clipData = ClipData.newPlainText(context.getString(R.string.copied_message), textToCopy);
                            clipboardManager.setPrimaryClip(clipData);
                        }
                    });
                }

                catch (Exception e) {
                }

                try {
                    alertDialogBuilder.create().show();
                }

                catch (Exception e) {
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}