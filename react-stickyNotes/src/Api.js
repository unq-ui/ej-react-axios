import axios from 'axios';

const getAllNotes = (authorization) => axios.get('http://localhost:7000/notes', { headers: { Authorization: authorization } })

const deleteNote = (authorization, noteId) => axios.delete(`http://localhost:7000/notes/${noteId}`, { headers: { Authorization: authorization } })

const updateNote = (authorization, note) => axios.put( `http://localhost:7000/notes/${note.id}`, { text: note.text, color: note.color }, { headers: { Authorization: authorization } } );

const addNote = (authorization, note) => axios.post( `http://localhost:7000/notes`, { text: note.text, color: note.color }, { headers: { Authorization: authorization } } );


export default {
  getAllNotes,
  deleteNote,
  updateNote,
  addNote,
};
