import {Layout, makeScene2D, Txt} from '@motion-canvas/2d';
import {
  all,
  createRef,
  createRefArray,
  DEFAULT,
  Direction,
  slideTransition,
  useLogger,
  waitUntil
} from '@motion-canvas/core';
import {Caption, Text} from '../../common';

export default makeScene2D(function* (view) {
  const title = createRef<Txt>();
  const textLines = createRefArray<Txt>();
  const link = createRef<Txt>();

  view.add(
    <>
      <Caption ref={title} text={'Out of scope'} />
      {/*<Layout layout size={'100%'} padding={[90, 120, 200]} direction={'column'} gap={100} alignItems={'center'}*/}
      {/*        textWrap={true}>*/}
      <Layout layout direction={'column'}>
        <Layout layout direction={'column'} gap={100}>
          <Text ref={textLines} text={'What are coroutines and how to use them'} />
          <Text ref={textLines} text={'What is structured concurrency'} />
          <Text ref={textLines} text={'What are dispatchers'} />
          <Text ref={textLines} text={'How to use channels and flows'} />
        </Layout>
        <Text ref={link} fill={'#548AF7'} text={'https://kotlinlang.org/docs/coroutines-guide.html'} opacity={0}
              marginTop={0} />
      </Layout>
      {/*</Layout>*/}
    </>
  );
  const h = link().height();
  const text = link().text();
  link().text('');
  link().height(0);
  for (const textLine of textLines) {
    textLine.opacity(0);
  }

  yield* slideTransition(Direction.Right);

  yield* waitUntil('start');

  title().size(title().size());
  yield* title().text('', 0.5);

  for (const textLine of textLines) {
    const text = textLine.text();
    textLine.size(textLine.size());
    textLine.text('');
    textLine.opacity(1);
    yield textLine.text(text, 1);
  }

  yield* waitUntil('link');
  yield* all(
    link().height(h, 1),
    link().margin.top(100, 1)
  );

  link().opacity(1);
  yield* link().text(text, 1);

  yield* waitUntil('end');
});
