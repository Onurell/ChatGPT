import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.firebase.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private lateinit var editTextEmail: TextInputEditText
    private lateinit var editTextPassword: TextInputEditText
    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        mAuth = FirebaseAuth.getInstance()
        editTextEmail = view.findViewById(R.id.email)
        editTextPassword = view.findViewById(R.id.password)

        val buttonLog = view.findViewById<Button>(R.id.btn_login)
        buttonLog.setOnClickListener {
            loginUser()
        }

        val textView = view.findViewById<TextView>(R.id.RegisterNow)
        textView.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return view
    }

    private fun loginUser() {
        val email = editTextEmail.text.toString().trim()
        val password = editTextPassword.text.toString().trim()

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(requireContext(), "Enter Email", Toast.LENGTH_SHORT).show()
            return
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(requireContext(), "Enter Password", Toast.LENGTH_SHORT).show()
            return
        }

        val progressBar = view?.findViewById<ProgressBar>(R.id.Progressbar)
        progressBar?.visibility = View.VISIBLE

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                progressBar?.visibility = View.GONE
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), "Login success.", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_loginFragment_to_chatgptFragment)
                } else {
                    Toast.makeText(requireContext(), "Login failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}

