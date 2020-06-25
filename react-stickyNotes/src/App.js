import React, { useEffect, useState } from 'react';
import './App.css';

import Api from './Api';
import StickyNote from './StickyNote';
import Modal from './Modal';

const App = () => {
  const [authorization, setAuthorization] = useState(1);
  const [notes, setNotes] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [selectedNote, setSelectedNote] = useState(null);
  const [error, setError] = useState('');

  useEffect(() => {
    Api.getAllNotes(authorization)
      .then(response => setNotes(response.data))
      .catch(() => setError('Se rompio todo T_T' ));
  }, [authorization]);

  const removeNote = id => {
    Api.deleteNote(authorization, id)
      .then(response => setNotes(response.data))
      .catch(() => setError('Se rompio todo T_T'));
  }

  const addNote = note => {
    let promise;
    if(note.id) {
      promise = Api.updateNote(authorization, note);
    } else {
      promise = Api.addNote(authorization, note);
    }

    promise
      .then(response => {
        setNotes(response.data);
        setSelectedNote(null);
      })
      .catch(() => setError('Se rompio todo T_T'));
  }

  const openModal = () => setShowModal(true);
  const closeModal = () => setShowModal(false);

  const editNote = note => {
    setSelectedNote(note);
    openModal();
  }

  if (error) return <div>{error}</div>

  return (
    <>
      <div className="container">
        {notes.map(note => <StickyNote key={note.id} note={note} removeNote={removeNote} editNote={() => editNote(note)} />)}
      </div>
      <div className="btn-add-note">
        <img src="./img/add.svg" alt="add new note" className="icon--add" onClick={openModal} />
      </div>
      <div className="btn-add-note2">
        <img src="./img/add.svg" alt="add new note" className="icon--add" onClick={() => setAuthorization(2)} />
      </div>
      {showModal && <Modal closeModal={closeModal} addNote={addNote} selectedNote={selectedNote} />}
    </>
  );
}

export default App;
