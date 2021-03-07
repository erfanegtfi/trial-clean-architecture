package com.example.trial_clean_architecture.ui.chat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trial_clean_architecture.R
import com.example.trial_clean_architecture.base.view.BaseActivity
import com.example.trial_clean_architecture.databinding.ActivityChatBinding
import com.example.trial_clean_architecture.model.ChatContent
import com.example.trial_clean_architecture.model.listTile.chat.ChatItemMyMessageTile
import com.example.trial_clean_architecture.model.listTile.chat.ChatItemPartnerMessageTile
import com.example.trial_clean_architecture.model.listTile.chat.ChatMessage
import com.example.trial_clean_architecture.ui.chat.adapter.ChatAdapter
import com.example.trial_clean_architecture.ui.chat.di.DaggerChatComponent
import com.example.trial_clean_architecture.utils.extentions.findAppComponent
import com.google.gson.Gson
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.json.JSONObject
import javax.inject.Inject


@ExperimentalCoroutinesApi
class ChatActivity : BaseActivity<ActivityChatBinding, ChatViewModel>() {

    override val layoutRes: Int = R.layout.activity_chat
    override val viewModelClass: Class<ChatViewModel> = ChatViewModel::class.java
    override val bindingVariable: Int? = null
    val gson: Gson = Gson()

    var messageList = mutableListOf<ChatMessage>()


    @Inject
    lateinit var mSocket: Socket
    lateinit var adapter: ChatAdapter
    var selectedUserID: Int=0

    companion object {
        const val USER_ID: String = "USER_ID"

        fun start(context: Context, userID: Int) {
            Intent(context, ChatActivity::class.java).apply {
                val bundle = Bundle().apply {
                    putInt(USER_ID, userID)
                }
                putExtras(bundle)
                context.startActivity(this)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerChatComponent.factory().create(findAppComponent()).inject(this)
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        bindView()

        intent.extras?.let {
//            selectedUserID = it.getInt(USER_ID)
            selectedUserID=16
        }

        adapter = ChatAdapter(
            ChatItemMyMessageTile,
            ChatItemPartnerMessageTile,
            items = messageList,
            context = this
        )

        recycleView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycleView.adapter = adapter


        mSocket.connect()
        mSocket.on(Socket.EVENT_CONNECT, onConnect)
        mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectionError)
        mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectionTimeOutError)
        mSocket.on(CHAT_EVENT, onChat)


        binding.imgSend.setOnClickListener {
            val text = edtMessage.text.toString().trim()
            if (text.isNotBlank()) {
                val messageObj = generateMessageObject(text)
                sendMessage(messageObj)
            }
        }
    }

    private fun generateMessageObject(message: String): JSONObject {
        val messageObject = JSONObject()
        messageObject.put("tran_id", selectedUserID)
        messageObject.put("message", message)
        return messageObject
    }

    private fun updateList(message: ChatMessage) {
        messageList.add(message)
        adapter.notifyItemInserted(messageList.size)
        recycleView.scrollToPosition(messageList.size - 1)
    }

    private fun sendMessage(message: JSONObject) {
        mSocket.emit(CHAT_EVENT, message)
        edtMessage.text.clear()
    }

    private var onChat = Emitter.Listener {
        it[0]?.let { res ->
            val chatMessage = gson.fromJson(res.toString(), ChatContent::class.java)
            runOnUiThread {
                updateList(ChatMessage.MyMessage(chatMessage))
            }
        }
    }

    private var onConnect = Emitter.Listener {
    }

    private var onConnectionError = Emitter.Listener {
    }

    private var onConnectionTimeOutError = Emitter.Listener {
    }


    override fun onDestroy() {
        super.onDestroy()
        mSocket.disconnect()
        mSocket.off(Socket.EVENT_CONNECT, onConnect)
        mSocket.off(Socket.EVENT_CONNECT_ERROR, onConnectionError)
        mSocket.off(Socket.EVENT_CONNECT_TIMEOUT, onConnectionTimeOutError)
        mSocket.off(CHAT_EVENT, onChat)
    }
}