package com.example.perfildeinvestidor

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.perfildeinvestidor.databinding.FragmentSeventhQuestionBinding


class SeventhQuestionFragment : Fragment() {

    private var binding: FragmentSeventhQuestionBinding? = null

    private val sharedViewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentSeventhQuestionBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {

            lifecycleOwner = viewLifecycleOwner

            viewModel = sharedViewModel

            seventhQuestionFragment = this@SeventhQuestionFragment
        }
    }

    fun goToNextScreen(questionIndex: Int) {

        sharedViewModel.hasNoQuestionChecked(questionIndex, 0)
        val id: Int = view?.findViewById<RadioGroup>(R.id.question_answers)!!.checkedRadioButtonId
        val selectedButton: RadioButton = binding!!.root.findViewById(id)

        Toast.makeText(
            activity,
            "Você escolheu a resposta ${selectedButton.text}.",
            Toast.LENGTH_LONG
        ).show()
        findNavController().navigate(R.id.action_seventhQuestionFragment_to_eighthQuestionFragment)

        }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed() {
                    sharedViewModel.removeLastScore()
                    findNavController().navigate(R.id.action_seventhQuestionFragment_to_sixthQuestionFragment)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}