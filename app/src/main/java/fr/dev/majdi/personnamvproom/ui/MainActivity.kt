package fr.dev.majdi.personnamvproom.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.dev.majdi.personnamvproom.R
import fr.dev.majdi.personnamvproom.adapter.PersonneAdapter
import fr.dev.majdi.personnamvproom.model.Personne
import fr.dev.majdi.personnamvproom.presenter.MainPresenter
import fr.dev.majdi.personnamvproom.utils.Utils
import fr.dev.majdi.personnamvproom.view.MainView
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), MainView {

    // Get instance presenter by Koin
    override val presenter: MainPresenter by inject {
        parametersOf(this)
    }

    private var personnaListAdapter: PersonneAdapter? = null
    private var personnes: MutableList<Personne> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        presenter.initRecycle()
        presenter.loadPersonnes()

        var gender = "Male"
        radio_gender.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = group.findViewById(checkedId)
            gender = radio.text.toString()
            //Log.e("MainActivity", gender)
        })

        save_button.setOnClickListener {
            Utils.hideSoftKeyBoard(this, it)

            val name = editName.text.toString()
            val email = editEmail.text.toString()

            val personne = Personne(null, name, email, gender)

            if (verifyInput()) {
                presenter.savePersonne(personne)
            }

        }
    }

    private fun verifyInput(): Boolean {
        var nameValue = ""
        var emailValue = ""
        if (editName.text != null)
            nameValue = editName.text!!.toString().trim { it <= ' ' }

        if (editEmail.text != null)
            emailValue = editEmail.text!!.toString().trim { it <= ' ' }

        val isValidateEmail: Boolean
        val isValidateName: Boolean

        if (TextUtils.isEmpty(nameValue)) {
            editName.error = "Name is empty"
            isValidateName = false
        } else {
            editName.error = null
            isValidateName = true
        }

        if (TextUtils.isEmpty(emailValue)) {
            editEmail.error = "Email is empty"
            isValidateEmail = false
        } else {
            editEmail.error = null
            isValidateEmail = true
        }

        return isValidateName && isValidateEmail
    }

    override fun initRecycleView() {
        recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        personnaListAdapter = PersonneAdapter(personnes)
        recycler.adapter = personnaListAdapter
        personnaListAdapter!!.setItemClickListener(object :
            PersonneAdapter.ItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                Toast.makeText(
                    this@MainActivity,
                    "Clicked User gender is ${personnes[position].gender}",
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onDeleteButtonClick(personne: Personne) {
                    presenter.deletePersonne(personne)
            }
        })
    }

    @SuppressLint("SetTextI18n")
    override fun setListPersonnes(personnesList: MutableList<Personne>) {
        personnes = personnesList
        if (personnaListAdapter != null)
            personnaListAdapter!!.setItems(personnes)
    }

    override fun setErrorLoadPersonnes() {
        Toast.makeText(this, "Load error", Toast.LENGTH_SHORT).show()
    }

    override fun setErrorSavePersonne(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun setSuccessSavePersonne(msg: String) {
        editName.setText("")
        editEmail.setText("")
        presenter.loadPersonnes()
    }

    override fun setErrorDeletePersonne(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun setSuccessDeletePersonne(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        presenter.loadPersonnes()
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.unSubscribe()
    }
}