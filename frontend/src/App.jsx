import React from 'react'
import Navbar from './components/Navbar'
import AppRoute from './routes/AppRoute'

const App = () => {
  return (
    <main className='w-full'>
      <Navbar/>
      <AppRoute/>
      
    </main>
  )
}

export default App