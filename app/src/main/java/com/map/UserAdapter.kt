package com.map

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.map.models.User
import java.text.Format

class UserAdapter(private val context: Context, private val users: List<User>) : BaseAdapter() {

    override fun getCount(): Int = users.size

    override fun getItem(position: Int): Any = users[position]

    override fun getItemId(position: Int): Long = position.toLong()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)

        // Lấy đối tượng User ở vị trí hiện tại
        val user = users[position]

        // Gán giá trị vào các TextView
        val tvFullname = view.findViewById<TextView>(R.id.tvFullname3)
        val tvUsername = view.findViewById<TextView>(R.id.tvUsername3)
        val tvEmail = view.findViewById<TextView>(R.id.tvEmail3)
        val tvDob = view.findViewById<TextView>(R.id.tvDob3)
        val tvGender = view.findViewById<TextView>(R.id.tvGender3)

        tvFullname.text = user.fullname
        tvUsername.text = user.username
        tvEmail.text = user.email
        tvGender.text = user.gender
        tvDob.text = Data.formatDate(user.dob)

        return view
    }
}
