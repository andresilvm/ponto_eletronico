import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import PontoEletronico from './components/PontoEletronico'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <PontoEletronico/>
  </StrictMode>,
)
