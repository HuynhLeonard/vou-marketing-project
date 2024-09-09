import React from 'react'
import Navigation from '../Components/Navigation'
import Homepage from "../Components/Homepage"
import NavigationTwo from '../Components/NavigationTwo'
import AdminEditAccountForm from '../Components/AdminForm/EditAccountForm'
import AdminAddAccountForm from '../Components/AdminForm/AddAccountForm'

const AdminHomePage = () => {
  return (
    <div>
      <NavigationTwo />
      <Homepage />
      <AdminAddAccountForm />
    </div>
  )
}

export default AdminHomePage