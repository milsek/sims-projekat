

const getBooklist = (start, finish) => {
    let booklist = [
        {title: "Aion",
        author: "C.G. Jung",
        reads: 267400,
        rating: 4.7,
        image: "book-placeholder5.png",
        id: 1,
        description: "Aion is one of a number of major works that Jung wrote during his seventies that were concerned with the relations between psychology, alchemy and religion."},
        {title: "Thus Spoke Zarathustra",
        author: "Friedrich Nietzsche",
        reads: 352630,
        rating: 5.0,
        image: "book-placeholder5.png",
        id: 2,
        description: "Nietzsche was one of the most revolutionary and subversive thinkers in Western philosophy, and Thus Spoke Zarathustra remains his most famous and influential work. It describes how the ancient Persian prophet Zarathustra descends from his solitude in the mountains to tell the world that God is dead and that the Superman, the human embodiment of divinity, is his successor."},
        {title: "The Ego and the Id",
        author: "Sigmund Freud",
        reads: 268000,
        rating: 4.6,
        image: "book-placeholder5.png",
        id: 3,
        description: "All human behaviors and traits, according to this 1923 study, derive from the complicated interactions of three elements of the psyche: the id, the ego, and the superego. The root of Sigmund Freud's approach to psychiatric treatment resides in bringing the id, the hidden source of human passion, to the surface."},
        {title: "Book Title 4",
        author: "Author Name",
        reads: 34000,
        rating: 4.1,
        image: "book-placeholder5.png",
        id: 4,
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce tempor metus ex, sollicitudin sagittis nisi posuere nec. Phasellus pharetra, sapien vitae lacinia porta, lacus sem egestas sapien, non tempor odio orci at elit."},
        {title: "Book Title 5",
        author: "Author Name",
        reads: 27000,
        rating: 4.7,
        image: "book-placeholder5.png",
        id: 5,
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce tempor metus ex, sollicitudin sagittis nisi posuere nec. Phasellus pharetra, sapien vitae lacinia porta, lacus sem egestas sapien, non tempor odio orci at elit."},
        {title: "Book Title 6",
        author: "Author Name",
        reads: 16000,
        rating: 4.4,
        image: "book-placeholder5.png",
        id: 6,
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce tempor metus ex, sollicitudin sagittis nisi posuere nec. Phasellus pharetra, sapien vitae lacinia porta, lacus sem egestas sapien, non tempor odio orci at elit."},
        {title: "Book Title 7",
        author: "Author Name",
        reads: 17000,
        rating: 4.3,
        image: "book-placeholder5.png",
        id: 7,
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce tempor metus ex, sollicitudin sagittis nisi posuere nec. Phasellus pharetra, sapien vitae lacinia porta, lacus sem egestas sapien, non tempor odio orci at elit."},
      ]
    if (booklist) {
        return booklist.slice(start, finish)
    }
    return []
  }

  export default getBooklist